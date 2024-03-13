package com.fsse2305.fsse2305_project_backend.data.cartItem.dto.response;

import com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject.CartItemDetailData;

public class DeleteCartItemResponseDto {
    private String result;

    public DeleteCartItemResponseDto(CartItemDetailData data) {
        setResult(data);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResult(CartItemDetailData data){
        if (data.getProduct() != null){
            this.result = "SUCCESS";
        } else {
            this.result = "FAILED";
        }
    }
}
