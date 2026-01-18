package com.rodriguezblanco.priceservice.prices.infrastructure.persistence;

import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.Period;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.ProductPriceIdentity;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.SellingPrice;
import com.rodriguezblanco.priceservice.prices.infrastructure.persistence.entity.Price;

public class PersistenceMapper {

    private PersistenceMapper() {
        // Avoid construction
    }

    public static ProductPrice toDomain(Price persistenceModel) {
        return ProductPrice.create(
                ProductPriceIdentity.of(
                        persistenceModel.brandId(),
                        persistenceModel.productId(),
                        persistenceModel.priceList()
                ),
                Period.of(
                        persistenceModel.startDate(),
                        persistenceModel.endDate()
                ),
                persistenceModel.priority(),
                SellingPrice.of(
                        persistenceModel.price(),
                        persistenceModel.currency()
                )
        );
    }

    public static Price toPersistence(ProductPrice domainModel) {
        return Price.of(
                domainModel.identity().productId(),
                domainModel.identity().brandId(),
                domainModel.identity().priceList(),
                domainModel.period().from(),
                domainModel.period().to(),
                domainModel.priority(),
                domainModel.sellingPrice().price(),
                domainModel.sellingPrice().currency()
        );
    }
}
