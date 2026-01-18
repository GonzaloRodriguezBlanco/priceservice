package com.rodriguezblanco.priceservice.prices.user.rest.request;

import com.rodriguezblanco.priceservice.support.user.rest.PathVariableException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public record ProductKey(Long productId, Integer brandId) {
    private static final String PATH_PATTERN = "^productId=[0-9]+,brandId=[0-9]+$";
    public static final String MESSAGE = "Format error. Variable format should be: \"productId=35455,brandId=1\"";

    public static ProductKey fromPath(String productKey) {

        if (!productKey.matches(PATH_PATTERN)) {
            throw new PathVariableException(MESSAGE);
        }
        String[] asArray = StringUtils.split(productKey, ",");
        Objects.requireNonNull(asArray);
        Long productId = Long.valueOf(Objects.requireNonNull(StringUtils.split(asArray[0], "="))[1]);
        Integer brandId = Integer.valueOf(Objects.requireNonNull(StringUtils.split(asArray[1], "="))[1]);
        return new ProductKey(productId, brandId);
    }
}
