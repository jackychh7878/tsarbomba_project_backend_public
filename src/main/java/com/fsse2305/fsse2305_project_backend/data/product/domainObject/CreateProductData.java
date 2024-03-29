package com.fsse2305.fsse2305_project_backend.data.product.domainObject;

import com.fsse2305.fsse2305_project_backend.data.product.dto.request.CreateProductRequestDto;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class CreateProductData {
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;

    public CreateProductData(CreateProductRequestDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.imageUrl = dto.getImageUrl();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
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
}
