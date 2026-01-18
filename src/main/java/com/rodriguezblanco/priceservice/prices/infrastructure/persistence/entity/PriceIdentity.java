package com.rodriguezblanco.priceservice.prices.infrastructure.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

public class PriceIdentity implements Serializable {
    private Long productId;
    private Integer brandId;
    private Integer priceList;

    public PriceIdentity() {
    }

    public PriceIdentity(Long productId, Integer brandId, Integer priceList) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PriceIdentity that = (PriceIdentity) o;
        return Objects.equals(productId, that.productId) && Objects.equals(brandId, that.brandId) && Objects.equals(priceList, that.priceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, brandId, priceList);
    }
}
