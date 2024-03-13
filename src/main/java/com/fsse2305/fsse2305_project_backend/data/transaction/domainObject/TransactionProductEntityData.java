package com.fsse2305.fsse2305_project_backend.data.transaction.domainObject;

import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class TransactionProductEntityData {
    private Integer tpid;
    private TransactionDetailData transaction;
    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private Integer quantity;
    private BigDecimal subtotal;
    private String priceId;

    public TransactionProductEntityData(TransactionProductEntity entity) {
        this.tpid = entity.getTpid();
        this.transaction = new TransactionDetailData(entity.getTransaction());
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
        this.quantity = entity.getQuantity();
        this.subtotal = entity.getSubtotal();
        this.priceId = entity.getPriceId();
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

    public TransactionDetailData getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDetailData transaction) {
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
}
