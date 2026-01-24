package com.rodriguezblanco.priceservice.prices.user.rest.request;

import com.rodriguezblanco.priceservice.support.user.rest.PathVariableException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Schema(description = "The product identity composed by productId and brand Id")
public record ProductKey(
        @Schema(description = "The unique identifier of a product", example = "35455", type = "integer", format = "int64")
        @NotBlank(message = "Should not be null")
        Long productId,
        @Schema(description = "The unique identifier of a brand", example = "1", type = "integer", format = "int32")
        @NotBlank(message = "Should not be null")
        Short brandId
) {
    private static final String PATH_PATTERN = "^productId=[0-9]+,brandId=[0-9]+$";
    public static final String MESSAGE = "Format error in productKey, given '%s'. Variable format should be like: 'productId=35455,brandId=1'";

    public static ProductKey fromPath(String productKey) {

        if (!productKey.matches(PATH_PATTERN)) {
            throw new PathVariableException(MESSAGE.formatted(productKey));
        }
        String[] asArray = StringUtils.split(productKey, ",");
        Objects.requireNonNull(asArray);
        Long productId = Long.valueOf(Objects.requireNonNull(StringUtils.split(asArray[0], "="))[1]);
        Short brandId = Short.valueOf(Objects.requireNonNull(StringUtils.split(asArray[1], "="))[1]);
        return new ProductKey(productId, brandId);
    }

    @Override
    public String toString() {
        return "productId=" + productId +",brandId=" + brandId;
    }
}
