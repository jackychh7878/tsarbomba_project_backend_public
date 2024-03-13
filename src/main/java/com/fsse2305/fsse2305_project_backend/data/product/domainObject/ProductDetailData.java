package com.fsse2305.fsse2305_project_backend.data.product.domainObject;

import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class ProductDetailData {
    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;
    private String imageUrl5;
    private String imageUrl6;
    private String imageUrl7;
    private String imageUrl8;
    private String imageUrl9;
    private String imageUrl10;
    private BigDecimal price;
    private Integer stock;
    private String priceId;

    public ProductDetailData(ProductEntity product) {
        this.pid = product.getPid();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.imageUrl2 = product.getImageUrl2();
        this.imageUrl3 = product.getImageUrl3();
        this.imageUrl4 = product.getImageUrl4();
        this.imageUrl5 = product.getImageUrl5();
        this.imageUrl6 = product.getImageUrl6();
        this.imageUrl7 = product.getImageUrl7();
        this.imageUrl8 = product.getImageUrl8();
        this.imageUrl9 = product.getImageUrl9();
        this.imageUrl10 = product.getImageUrl10();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.priceId = product.getPriceId();
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
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

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public String getImageUrl6() {
        return imageUrl6;
    }

    public void setImageUrl6(String imageUrl6) {
        this.imageUrl6 = imageUrl6;
    }

    public String getImageUrl7() {
        return imageUrl7;
    }

    public void setImageUrl7(String imageUrl7) {
        this.imageUrl7 = imageUrl7;
    }

    public String getImageUrl8() {
        return imageUrl8;
    }

    public void setImageUrl8(String imageUrl8) {
        this.imageUrl8 = imageUrl8;
    }

    public String getImageUrl9() {
        return imageUrl9;
    }

    public void setImageUrl9(String imageUrl9) {
        this.imageUrl9 = imageUrl9;
    }

    public String getImageUrl10() {
        return imageUrl10;
    }

    public void setImageUrl10(String imageUrl10) {
        this.imageUrl10 = imageUrl10;
    }
}
