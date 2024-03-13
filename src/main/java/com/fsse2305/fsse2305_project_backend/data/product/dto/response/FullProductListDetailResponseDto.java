package com.fsse2305.fsse2305_project_backend.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2305.fsse2305_project_backend.data.product.domainObject.ProductDetailData;

import java.math.BigDecimal;

@JsonPropertyOrder({"pid", "name", "imageUrl", "imageUrl2", "price", "stock"})
public class FullProductListDetailResponseDto {
    private Integer pid;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("image_url_2")
    private String imageUrl2;
    private BigDecimal price;
    @JsonProperty("has_stock")
    private Boolean hasStock;
    private String priceId;

    public FullProductListDetailResponseDto(ProductDetailData data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.imageUrl = data.getImageUrl();
        this.imageUrl2 = data.getImageUrl2();
        this.price = data.getPrice();
        this.priceId = data.getPriceId();
        setHasStock(data);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(ProductDetailData data) {
        if (data.getStock() > 0){
            this.hasStock = true;
        } else {
            this.hasStock = false;
        }
    }
}
