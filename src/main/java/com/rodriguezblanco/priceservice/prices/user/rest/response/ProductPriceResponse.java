package com.rodriguezblanco.priceservice.prices.user.rest.response;

import com.rodriguezblanco.priceservice.prices.domain.valueobject.Currency;

import java.time.LocalDateTime;

public record ProductPriceResponse(
        Long productId,
        Short brandId,
        Short priceList,
        LocalDateTime from,
        LocalDateTime to,
        Double sellingPrice,
        Currency currency
) {
    public static ProductPriceResponse of(Long productId,
                     Short brandId,
                     Short priceList,
                     LocalDateTime from,
                     LocalDateTime to,
                     Double sellingPrice,
                     Currency currency) {
        return new ProductPriceResponse(productId, brandId, priceList, from, to, sellingPrice, currency);
    }
}
