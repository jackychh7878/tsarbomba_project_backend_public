package com.fsse2305.fsse2305_project_backend.service;

import com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject.CartItemDetailData;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;

import java.util.List;

public interface CartItemService {

    CartItemDetailData putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemDetailData> getUserCart(FirebaseUserData firebaseUserData);

    CartItemDetailData patchCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    CartItemDetailData deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    void clearCartItemByUid(FirebaseUserData firebaseUserData, Integer uid);
}
