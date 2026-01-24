package com.rodriguezblanco.priceservice.unit;

import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.Currency;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.Period;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.ProductPriceIdentity;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.SellingPrice;

import java.time.LocalDateTime;

public class Mother {

    public static Long defaultProductId() {
        return 35455L;
    }

    public static Short defaultBrandId() {
        return 1;
    }

    public static Short defaultPriceList() {
        return 1;
    }

    public static LocalDateTime defaultQueryDate() {
        return LocalDateTime.parse("2020-06-14T10:00:00");
    }

    public static ProductPrice defaultProductPrice() {
        Short priority = 0;

        return ProductPrice.create(
                ProductPriceIdentity.of(
                        defaultBrandId(),
                        defaultProductId(),
                        defaultPriceList()
                ),
                Period.of(
                        LocalDateTime.parse("2020-06-14T00:00:00"),
                        LocalDateTime.parse("2020-12-31T23:59:59")
                ),
                priority,
                SellingPrice.of(
                        35.50,
                        Currency.EUR
                )
        );
    }
}
