package com.rodriguezblanco.priceservice.prices.domain.valueobject;

public class ProductPriceIdentity {
    private final Short brandId;
    private final Long productId;
    private final Short priceList;

    public Short brandId() {
        return brandId;
    }

    public Long productId() {
        return productId;
    }

    public Short priceList() {
        return priceList;
    }

    private ProductPriceIdentity(Short brandId, Long productId, Short priceList) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
    }

    public static ProductPriceIdentity of(Short brandId, Long productPrice, Short priceList) {
        return new ProductPriceIdentity(brandId, productPrice, priceList);
    }
}
