package com.fsse2305.fsse2305_project_backend.data.cartItem.entity;

import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.UserDetailData;
import com.fsse2305.fsse2305_project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", unique = true, nullable = false)
    private ProductEntity product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;
    @Column(nullable = false)
    private Integer quantity;

    public CartItemEntity(){};

    public CartItemEntity(UserEntity user, ProductEntity product, Integer quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
