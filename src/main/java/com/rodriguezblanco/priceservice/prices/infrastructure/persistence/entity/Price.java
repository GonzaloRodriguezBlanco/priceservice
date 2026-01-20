package com.rodriguezblanco.priceservice.prices.infrastructure.persistence.entity;

import com.rodriguezblanco.priceservice.prices.domain.valueobject.Currency;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(
        name = "PRICES",
        indexes = {
                @Index(name = "start_date_end_date_idx", columnList = "startDate, endDate"),
                @Index(name = "priority_sort_idx", columnList = "priority DESC")
        }
)
@Entity
@IdClass(PriceIdentity.class)
public class Price {
    @Id
    private Long productId;
    @Id
    @Column(columnDefinition = "SMALLINT")
    private Short brandId;
    @Id
    @Column(columnDefinition = "SMALLINT")
    private Short priceList;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(columnDefinition = "SMALLINT")
    private Short priority;
    private Double price;
    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private Currency currency;

    public Price() {
    }

    public Price(Long productId,
                 Short brandId,
                 Short priceList,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 Short priority,
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

    public Short brandId() {
        return brandId;
    }

    public Short priceList() {
        return priceList;
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public Short priority() {
        return priority;
    }

    public Double price() {
        return price;
    }

    public Currency currency() {
        return currency;
    }

    public static Price of(Long productId,
                           Short brandId,
                           Short priceList,
                           LocalDateTime startDate,
                           LocalDateTime endDate,
                           Short priority,
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
