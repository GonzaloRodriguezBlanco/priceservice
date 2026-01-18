package com.rodriguezblanco.priceservice.prices.application;

import com.rodriguezblanco.priceservice.support.application.query.Query;

import java.time.LocalDateTime;

public record GetPriceOnDateQuery(
        Integer brandId,
        Long productId,
        LocalDateTime date
) implements Query {

}
