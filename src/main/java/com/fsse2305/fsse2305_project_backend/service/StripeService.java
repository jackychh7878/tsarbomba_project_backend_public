package com.fsse2305.fsse2305_project_backend.service;

import com.fsse2305.fsse2305_project_backend.data.stripe.StripeProduct;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface StripeService {
//    Session createCheckoutSession(Integer tid, List<StripeProduct> stripeProductList) throws StripeException;
Session createCheckoutSession(List<StripeProduct> stripeProductList) throws StripeException;
}
