package com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject;

import com.fsse2305.fsse2305_project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.UserDetailData;
import com.fsse2305.fsse2305_project_backend.data.user.entity.UserEntity;


public class CartItemDetailData {
    private Integer cid;
    private ProductEntity product;
    private UserDetailData user;
    private Integer quantity;

    public CartItemDetailData(CartItemEntity cartItemEntity){
        this.cid = cartItemEntity.getCid();
        this.product = cartItemEntity.getProduct();
        this.user = new UserDetailData(cartItemEntity.getUser());
        this.quantity = cartItemEntity.getQuantity();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserDetailData getUser() {
        return user;
    }

    public void setUser(UserDetailData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
