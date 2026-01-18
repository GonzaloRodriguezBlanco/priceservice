package com.rodriguezblanco.priceservice.prices.user.rest;

import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.user.rest.response.ProductPriceResponse;

public class RestMapper {
    private RestMapper() {
        // Avoid construction
    }

    public static ProductPriceResponse toResponse(ProductPrice domainModel) {
        return ProductPriceResponse.of(
                domainModel.identity().productId(),
                domainModel.identity().brandId(),
                domainModel.identity().priceList(),
                domainModel.period().from(),
                domainModel.period().to(),
                domainModel.sellingPrice().price(),
                domainModel.sellingPrice().currency()
        );
    }
}
