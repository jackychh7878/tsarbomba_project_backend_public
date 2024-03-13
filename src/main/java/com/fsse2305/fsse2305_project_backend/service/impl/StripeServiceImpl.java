package com.fsse2305.fsse2305_project_backend.service.impl;

import com.fsse2305.fsse2305_project_backend.config.EnvConfig;
import com.fsse2305.fsse2305_project_backend.data.stripe.StripeProduct;

import com.fsse2305.fsse2305_project_backend.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StripeServiceImpl implements StripeService {


    public StripeServiceImpl() {
        Stripe.apiKey = "";
    }

    @Override
    public Session createCheckoutSession(List<StripeProduct> stripeProductList) throws StripeException {
        String baseUrl = System.getProperty("environment", "dev").equals("prod") ? EnvConfig.prodConfig : EnvConfig.devConfig;
        List<LineItem> lineItems = new ArrayList<>();

        for (StripeProduct product : stripeProductList) {
            LineItem lineItem = LineItem.builder()
                    .setQuantity(Long.valueOf(product.getQuantity()))
                    .setPrice(product.getPriceId())
                    .build();

            lineItems.add(lineItem);
        }


        SessionCreateParams params =
                SessionCreateParams.builder()

                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("https://www.tsarbombaproject.com" + "/#/thankyou/")
                        .setCancelUrl("https://www.tsarbombaproject.com" + "/#/cancelled/")
                        .addAllLineItem(lineItems)
                        .build();

        return Session.create(params);
    }
}