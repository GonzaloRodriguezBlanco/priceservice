package com.rodriguezblanco.priceservice.prices.domain;

import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.domain.exception.PriceNotFoundException;

import java.time.LocalDateTime;

public interface ProductPriceRepository {
    ProductPrice findProductPriceInDate(Integer brandId, Long productId, LocalDateTime date) throws PriceNotFoundException;
    ProductPrice save(ProductPrice productPrice);
}
