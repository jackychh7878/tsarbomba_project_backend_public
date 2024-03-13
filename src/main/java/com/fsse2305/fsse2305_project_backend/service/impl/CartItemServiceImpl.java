package com.fsse2305.fsse2305_project_backend.service.impl;

import com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject.CartItemDetailData;
import com.fsse2305.fsse2305_project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2305.fsse2305_project_backend.data.user.entity.UserEntity;
import com.fsse2305.fsse2305_project_backend.exception.cartItem.AddItemToCartException;
import com.fsse2305.fsse2305_project_backend.exception.cartItem.DeleteCartItemException;
import com.fsse2305.fsse2305_project_backend.exception.cartItem.UpdateCartItemException;
import com.fsse2305.fsse2305_project_backend.exception.product.ProductNotFoundException;
import com.fsse2305.fsse2305_project_backend.repository.CartRepository;
import com.fsse2305.fsse2305_project_backend.service.CartItemService;
import com.fsse2305.fsse2305_project_backend.service.ProductService;
import com.fsse2305.fsse2305_project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    Logger logger = LoggerFactory.getLogger(getClass());
    String warnTitle, warnReason;
    private final UserService userService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartRepository cartRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItemDetailData putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        try{
            UserEntity loginUser = getUserEntity(firebaseUserData);
            warnTitle = "AddItemToCartFailed: ";
            if ((pid == null) || (quantity == null)){
                warnReason = "Attribute is null";
                throw new AddItemToCartException();
            }
            //Check if input quantity valid
            if (quantity <= 0){
                warnReason = "Quantity cannot be equal or less than 0";
                throw new AddItemToCartException();
            }
            ProductEntity productToCart = productService.findProductEntityById(pid);
            if (productToCart.getStock() < quantity){
                warnReason = "Quantity cannot be larger than stock number";
                throw new AddItemToCartException();
            }
            //Check if item already existed in current cart
            Optional<CartItemEntity> currentCartItemEntity = cartRepository.findProductFromCartByUserAndProductId(loginUser.getUid(), pid);
            if (currentCartItemEntity.isEmpty()){
                CartItemEntity cartItemEntity = new CartItemEntity(loginUser, productToCart, quantity);
                cartRepository.save(cartItemEntity);
                return new CartItemDetailData(cartItemEntity);
            } else {
                if (productToCart.getStock() < (currentCartItemEntity.get().getQuantity() + quantity)){
                    warnReason = "Quantity cannot be larger than stock number";
                    throw new AddItemToCartException();
                }
                currentCartItemEntity.get().setQuantity(currentCartItemEntity.get().getQuantity() + quantity);
                cartRepository.save(currentCartItemEntity.get());
                return new CartItemDetailData(currentCartItemEntity.get());
            }
        } catch (ProductNotFoundException ex){
            warnReason = "Product not found";
            logger.warn(warnTitle + warnReason);
            throw new AddItemToCartException(warnTitle + warnReason);
        } catch (AddItemToCartException ex){
            logger.warn(warnTitle + warnReason);
            throw new AddItemToCartException(warnTitle + warnReason);
        } catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<CartItemDetailData> getUserCart(FirebaseUserData firebaseUserData){
        UserEntity loginUser = getUserEntity(firebaseUserData);
        List<CartItemDetailData> cartItemDetailDataList = new ArrayList<>();

        for (CartItemEntity entity: cartRepository.getUserCartItem(loginUser.getUid())){
            cartItemDetailDataList.add(new CartItemDetailData(entity));
        }
        return cartItemDetailDataList;
    }

    @Override
    public CartItemDetailData patchCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        try{
            UserEntity loginUser = userService.getEntityByFirebaseUserData(firebaseUserData);
            warnTitle = "UpdateCartItemFailed: ";
            if ((pid == null) || (quantity == null)){
                warnReason = "Attribute is null";
                throw new UpdateCartItemException();
            }
            if (quantity < 0){
                warnReason = "Quantity cannot be less than 0";
                throw new UpdateCartItemException();
            }
            //Check if the product id is valid
            Optional<CartItemEntity> cartItemEntity = cartRepository.findProductFromCartByUserAndProductId(loginUser.getUid(), pid);
            if (cartItemEntity.isEmpty()){
                warnReason = "Product not found from the cart - pid: " + pid;
                throw new UpdateCartItemException();
            }
            //If quantity = 0 delete the cart item
            if (quantity == 0){
                cartRepository.deleteCartItemByUserAndProductId(loginUser.getUid(), pid);
                return new CartItemDetailData(cartItemEntity.get());
            }
            //Check if stock still available
            ProductEntity productToCart = productService.findProductEntityById(pid);
            if (productToCart.getStock() < quantity){
                warnReason = "Quantity cannot be larger than stock number";
                throw new UpdateCartItemException();
            }
            cartItemEntity.get().setQuantity(quantity);
            return new CartItemDetailData(cartRepository.save(cartItemEntity.get()));
        } catch (ProductNotFoundException ex){
            warnReason = "Product not found";
            logger.warn(warnTitle + warnReason);
            throw new UpdateCartItemException(warnTitle + warnReason);
        } catch (UpdateCartItemException ex){
            logger.warn(warnTitle + warnReason);
            throw new UpdateCartItemException(warnTitle + warnReason);
        } catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public CartItemDetailData deleteCartItem(FirebaseUserData firebaseUserData, Integer pid){
        try{
            UserEntity loginUser = getUserEntity(firebaseUserData);
            warnTitle = "DeleteItemFromCartFailed: ";
            if (pid == null){
                warnReason = "Attribute is null ";
                throw new DeleteCartItemException();
            }
            //Check if the product id is valid
            Optional<CartItemEntity> cartItemEntity = cartRepository.findProductFromCartByUserAndProductId(loginUser.getUid(), pid);
            if (cartItemEntity.isEmpty()){
                warnReason = "Delete product not found - pid: " + pid;
                throw new DeleteCartItemException();
            }
            cartRepository.deleteCartItemByUserAndProductId(loginUser.getUid(), pid);
            return new CartItemDetailData(cartItemEntity.get());
        } catch (DeleteCartItemException ex){
            logger.warn(warnTitle + warnReason);
            throw new UpdateCartItemException(warnTitle + warnReason);
        } catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public void clearCartItemByUid(FirebaseUserData firebaseUserData, Integer uid){
        UserEntity loginUser = getUserEntity(firebaseUserData);
        cartRepository.clearCartByUserId(uid);
    }


    public UserEntity getUserEntity(FirebaseUserData firebaseUserData){
        return userService.getEntityByFirebaseUserData(firebaseUserData);
    }

//    public CartItemEntity getEntityByUidAndPid(Integer uid, Integer pid){
//        Optional<CartItemEntity> optionalCartItemEntity = cartRepository.findProductFromCartByUserAndProductId(uid, pid);
//        return optionalCartItemEntity.orElseThrow()
//    }

}
