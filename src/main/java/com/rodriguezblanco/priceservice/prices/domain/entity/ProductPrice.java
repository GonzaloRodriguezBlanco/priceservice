package com.rodriguezblanco.priceservice.prices.domain.entity;

import com.rodriguezblanco.priceservice.prices.domain.valueobject.Period;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.ProductPriceIdentity;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.SellingPrice;

public class ProductPrice {
    private final ProductPriceIdentity identity;
    private final Period period;
    private final Integer priority;
    private final SellingPrice sellingPrice;

    public ProductPriceIdentity identity() {
        return identity;
    }

    public Period period() {
        return period;
    }

    public Integer priority() {
        return priority;
    }

    public SellingPrice sellingPrice() {
        return sellingPrice;
    }

    private ProductPrice(ProductPriceIdentity identity, Period period, Integer priority, SellingPrice sellingPrice) {
        this.identity = identity;
        this.period = period;
        this.priority = priority;
        this.sellingPrice = sellingPrice;
    }

    public static ProductPrice create(ProductPriceIdentity identity, Period period, Integer priority, SellingPrice sellingPrice) {
        return new ProductPrice(
                identity,
                period,
                priority,
                sellingPrice
        );
    }
}
