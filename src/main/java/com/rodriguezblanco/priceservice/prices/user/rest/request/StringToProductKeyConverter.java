package com.rodriguezblanco.priceservice.prices.user.rest.request;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProductKeyConverter implements Converter<String, ProductKey> {

    @Override
    public ProductKey convert(String source) {
        return ProductKey.fromPath(source);
    }
}
