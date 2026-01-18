package com.rodriguezblanco.priceservice.prices.infrastructure.persistence.entity;

import com.rodriguezblanco.priceservice.prices.domain.valueobject.Currency;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "PRICES")
@Entity
@IdClass(PriceIdentity.class)
public class Price {
    @Id
    private Long productId;
    @Id
    private Integer brandId;
    @Id
    private Integer priceList;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Price() {
    }

    public Price(Long productId,
                 Integer brandId,
                 Integer priceList,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 Integer priority,
                 Double price,
                 Currency currency) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public Long productId() {
        return productId;
    }

    public Integer brandId() {
        return brandId;
    }

    public Integer priceList() {
        return priceList;
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public Integer priority() {
        return priority;
    }

    public Double price() {
        return price;
    }

    public Currency currency() {
        return currency;
    }

    public static Price of(Long productId,
                           Integer brandId,
                           Integer priceList,
                           LocalDateTime startDate,
                           LocalDateTime endDate,
                           Integer priority,
                           Double price,
                           Currency currency) {
        return new Price(productId,
                brandId,
                priceList,
                startDate,
                endDate,
                priority,
                price,
                currency
        );
    }
}
