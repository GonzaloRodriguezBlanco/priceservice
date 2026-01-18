package com.rodriguezblanco.priceservice.prices.domain.valueobject;


public class SellingPrice {
    private final Double price;
    private final Currency currency;

    private SellingPrice(Double price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    public Double price() {
        return price;
    }

    public Currency currency() {
        return currency;
    }

    public static SellingPrice of(Double price, Currency currency) {
        return new SellingPrice(price, currency);
    }
}
