package com.rodriguezblanco.priceservice.prices.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    private static final String MSG_FOR_BRAND_AND_PRODUCT="Price not found for (brand: %d, product: %d, date: %s).";

    public PriceNotFoundException(String message) {
        super(message);
    }

    public static PriceNotFoundException forBrandProductAndDate(Integer brandId, Long productId, LocalDateTime date) {
        return new PriceNotFoundException(MSG_FOR_BRAND_AND_PRODUCT.formatted(brandId, productId, date));
    }
}
