package com.rodriguezblanco.priceservice.prices.user.rest.response;

import com.rodriguezblanco.priceservice.prices.domain.valueobject.Currency;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description="The price corresponding to the provided 'productKey' on given date")
public record ProductPriceResponse(
        @Schema(description="The price corresponding to the provided 'productKey' on given date")
        Long productId,
        Short brandId,
        Short priceList,
        @Schema(description="Start date on which the price applies")
        LocalDateTime from,
        @Schema(description="End date on which the price applies")
        LocalDateTime to,
        @Schema(description="Final selling price")
        Double sellingPrice,
        @Schema(description="Currency")
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
