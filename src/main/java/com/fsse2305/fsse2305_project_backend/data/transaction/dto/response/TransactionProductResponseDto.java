package com.fsse2305.fsse2305_project_backend.data.transaction.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject.CartItemDetailData;
import com.fsse2305.fsse2305_project_backend.data.cartItem.dto.response.CartItemResponseDto;
import com.fsse2305.fsse2305_project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2305.fsse2305_project_backend.data.product.domainObject.ProductDetailData;
import com.fsse2305.fsse2305_project_backend.data.product.dto.response.ProductDetailResponseDto;
import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import com.fsse2305.fsse2305_project_backend.data.transaction.domainObject.TransactionProductEntityData;

import java.math.BigDecimal;
import java.util.List;

@JsonPropertyOrder({"tpid", "productDetailResponseDto", "quantity", "subtotal"})
public class TransactionProductResponseDto {
    private Integer tpid;
    @JsonProperty("product")
    private ProductDetailResponseDto productDetailResponseDto;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransactionProductResponseDto(TransactionProductEntityData data) {
        this.tpid = data.getTpid();
        this.productDetailResponseDto = new ProductDetailResponseDto(new ProductDetailData(new ProductEntity()));
        productDetailResponseDto.setPid(data.getPid());
        productDetailResponseDto.setName(data.getName());
        productDetailResponseDto.setDescription(data.getDescription());
        productDetailResponseDto.setImageUrl(data.getImageUrl());
        productDetailResponseDto.setPrice(data.getPrice());
        productDetailResponseDto.setStock(data.getStock());
        productDetailResponseDto.setPriceId(data.getPriceId());
        this.quantity = data.getQuantity();
        this.subtotal = data.getSubtotal();
    }

    public ProductDetailResponseDto getProductDetailResponseDto() {
        return productDetailResponseDto;
    }

    public void setProductDetailResponseDto(ProductDetailResponseDto productDetailResponseDto) {
        this.productDetailResponseDto = productDetailResponseDto;
    }

    public void setCartItemResponseDto(TransactionProductEntityData data){

    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
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
