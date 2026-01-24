package com.rodriguezblanco.priceservice.prices.infrastructure.persistence;

import com.rodriguezblanco.priceservice.prices.domain.ProductPriceRepository;
import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.domain.exception.PriceNotFoundException;
import com.rodriguezblanco.priceservice.prices.infrastructure.persistence.entity.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

    JpaProductPriceRepository jpaProductPriceRepository;

    public ProductPriceRepositoryImpl(JpaProductPriceRepository productPriceRepository) {
        this.jpaProductPriceRepository = productPriceRepository;
    }

    @Override
    public ProductPrice findProductPriceInDate(Short brandId, Long productId, LocalDateTime date) throws PriceNotFoundException {
        Price price = this.jpaProductPriceRepository.findByProductIdBrandIdAndDate(productId, brandId, date);
        if (null == price) throw PriceNotFoundException.forBrandProductAndDate(brandId, productId, date);
        return PersistenceMapper.toDomain(price);    }

    @Override
    public ProductPrice save(ProductPrice productPrice) {
        this.jpaProductPriceRepository.save(PersistenceMapper.toPersistence(productPrice));
        return productPrice;
    }
}
