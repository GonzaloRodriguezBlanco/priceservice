package com.rodriguezblanco.priceservice.prices.domain.valueobject;

public class ProductPriceIdentity {
    private final Integer brandId;
    private final Long productId;
    private final Integer priceList;

    public Integer brandId() {
        return brandId;
    }

    public Long productId() {
        return productId;
    }

    public Integer priceList() {
        return priceList;
    }

    private ProductPriceIdentity(Integer brandId, Long productId, Integer priceList) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
    }

    public static ProductPriceIdentity of(Integer brandId, Long productPrice, Integer priceList) {
        return new ProductPriceIdentity(brandId, productPrice, priceList);
    }
}
