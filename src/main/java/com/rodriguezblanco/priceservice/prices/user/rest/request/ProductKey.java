package com.rodriguezblanco.priceservice.prices.user.rest.request;

import com.rodriguezblanco.priceservice.support.user.rest.PathVariableException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Schema(description = "The product identity composed by productId and brand Id")
public record ProductKey(
        @Schema(description = "The unique identifier of a product", example = "35455", type = "integer", format = "int64")
        @NotNull
        Long productId,
        @Schema(description = "The unique identifier of a brand", example = "1", type = "integer", format = "int32")
        @NotNull
        Integer brandId
) {
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
