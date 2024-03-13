package com.fsse2305.fsse2305_project_backend.data.stripe;

public class StripeProduct {

    private String priceId;
    private int quantity;

    public StripeProduct(String priceId, int quantity) {
        this.priceId = priceId;
        this.quantity = quantity;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
