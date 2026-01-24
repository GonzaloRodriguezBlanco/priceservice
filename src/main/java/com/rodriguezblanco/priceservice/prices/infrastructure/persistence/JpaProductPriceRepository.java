package com.rodriguezblanco.priceservice.prices.infrastructure.persistence;

import com.rodriguezblanco.priceservice.prices.infrastructure.persistence.entity.Price;
import com.rodriguezblanco.priceservice.prices.infrastructure.persistence.entity.PriceIdentity;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface JpaProductPriceRepository extends Repository<Price, PriceIdentity> {
    @NativeQuery(
            "SELECT " +
                    "product_id, " +
                    "brand_id, " +
                    "price_list, " +
                    "start_date, " +
                    "end_date, " +
                    "priority, " +
                    "price, " +
                    "currency " +
                "FROM PRICES  " +
                "WHERE " +
                    "product_id = :productId AND brand_id = :brandId AND " +
                    ":date BETWEEN start_date AND end_date " +
                "ORDER BY priority DESC " +
                "LIMIT 1"
    )
    Price findByProductIdBrandIdAndDate(Long productId, Short brandId, @Param("date") LocalDateTime date);

    Price save(Price price);
}
