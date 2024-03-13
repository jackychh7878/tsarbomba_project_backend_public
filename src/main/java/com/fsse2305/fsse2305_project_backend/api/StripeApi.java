package com.fsse2305.fsse2305_project_backend.api;

import com.fsse2305.fsse2305_project_backend.config.EnvConfig;
import com.fsse2305.fsse2305_project_backend.data.stripe.StripeProduct;
import com.fsse2305.fsse2305_project_backend.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RestController
public class StripeApi {

    @Autowired
    private StripeService stripeService;


    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(@RequestBody List<StripeProduct> stripeProductsList) {
        try {
            Session session = stripeService.createCheckoutSession(stripeProductsList);
            return session.getUrl();
        } catch (StripeException e) {
            e.printStackTrace();
            return "/error";
        }
    }

}
