package com.fsse2305.fsse2305_project_backend.api;

import com.fsse2305.fsse2305_project_backend.config.EnvConfig;
import com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject.CartItemDetailData;
import com.fsse2305.fsse2305_project_backend.data.cartItem.dto.response.AddCartItemResponseDto;
import com.fsse2305.fsse2305_project_backend.data.cartItem.dto.response.DeleteCartItemResponseDto;
import com.fsse2305.fsse2305_project_backend.data.cartItem.dto.response.GetUserCartResponseDto;
import com.fsse2305.fsse2305_project_backend.data.cartItem.dto.response.UpdateCartItemResponseDto;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2305.fsse2305_project_backend.service.CartItemService;
import com.fsse2305.fsse2305_project_backend.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RequestMapping("/cart")
@RestController
public class CartItemApi {
    private final CartItemService cartItemService;

    @Autowired
    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public AddCartItemResponseDto putCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new AddCartItemResponseDto(cartItemService.putCartItem(firebaseUserData, pid, quantity));
    }

    @GetMapping
    public List<GetUserCartResponseDto> getUserCart(JwtAuthenticationToken jwtToken){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        List<GetUserCartResponseDto> getUserCartResponseDtoList = new ArrayList<>();

        for (CartItemDetailData cartItemDetailData: cartItemService.getUserCart(firebaseUserData)){
            getUserCartResponseDtoList.add(new GetUserCartResponseDto(cartItemDetailData));
        }
        return getUserCartResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public UpdateCartItemResponseDto patchCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new UpdateCartItemResponseDto(cartItemService.patchCartItem(firebaseUserData, pid, quantity));
    }

    @DeleteMapping("/{pid}")
    public DeleteCartItemResponseDto deleteCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new DeleteCartItemResponseDto(cartItemService.deleteCartItem(firebaseUserData, pid));
    }

}
