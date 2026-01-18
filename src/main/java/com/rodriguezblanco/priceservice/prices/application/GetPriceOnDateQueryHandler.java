package com.rodriguezblanco.priceservice.prices.application;

import com.rodriguezblanco.priceservice.prices.domain.ProductPriceRepository;
import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.ProductPriceIdentity;
import com.rodriguezblanco.priceservice.support.application.query.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GetPriceOnDateQueryHandler implements QueryHandler<GetPriceOnDateQuery, ProductPrice> {
    private final ProductPriceRepository productPriceRepository;

    public GetPriceOnDateQueryHandler(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public ProductPrice handle(GetPriceOnDateQuery query) {
        return this.productPriceRepository
                .findProductPriceInDate(
                        query.brandId(),
                        query.productId(),
                        query.date()
                );
    }
}
