package com.fsse2305.fsse2305_project_backend.data.transaction.entity;

import com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject.CartItemDetailData;
import com.fsse2305.fsse2305_project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction_product")
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", nullable = false)
    private TransactionEntity transaction;
    @Column(nullable = false)
    private Integer pid;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(name= "stripe_price_id")
    private String priceId;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal subtotal;

    public TransactionProductEntity() {
    }

    //Fix the constructor
    public TransactionProductEntity(TransactionEntity transactionEntity, CartItemDetailData cartItemEntity){
        this.pid = cartItemEntity.getProduct().getPid();
        this.transaction = transactionEntity;
        this.name = cartItemEntity.getProduct().getName();
        this.description = cartItemEntity.getProduct().getDescription();
        this.imageUrl = cartItemEntity.getProduct().getImageUrl();
        this.price = cartItemEntity.getProduct().getPrice();
        this.stock = cartItemEntity.getProduct().getStock();
        this.quantity = cartItemEntity.getQuantity();
        this.priceId = cartItemEntity.getProduct().getPriceId();
        setSubtotal(cartItemEntity.getProduct().getPrice(), cartItemEntity.getQuantity());
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setSubtotal(BigDecimal price, Integer quantity){
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
    }
}
