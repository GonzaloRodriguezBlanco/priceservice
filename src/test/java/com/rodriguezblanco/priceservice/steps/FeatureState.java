package com.rodriguezblanco.priceservice.steps;

import com.rodriguezblanco.priceservice.prices.user.rest.response.ProductPriceResponse;
import org.springframework.stereotype.Component;

@Component
public class FeatureState {
    private ProductPriceResponse price;

    public ProductPriceResponse price() {
        return price;
    }

    public void setPrice(ProductPriceResponse price) {
        this.price = price;
    }
}
