package com.rodriguezblanco.priceservice.prices.user.rest;

import com.rodriguezblanco.priceservice.prices.application.GetPriceOnDateQuery;
import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.user.rest.request.ProductKey;
import com.rodriguezblanco.priceservice.prices.user.rest.response.ProductPriceResponse;
import com.rodriguezblanco.priceservice.support.application.query.Query;
import com.rodriguezblanco.priceservice.support.application.userports.QueryBus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@Tag(name= "ProductPrice")
@RestController
@RequestMapping("/api")
public class GetProductPriceInDateController {

    final QueryBus<Query> queryBus;

    public GetProductPriceInDateController(QueryBus<Query> queryBus) {
        this.queryBus = queryBus;
    }


    @Operation(summary = "Query the active product price at a given date")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Found the price",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductPriceResponse.class))}
            )
    )
    @GetMapping(
            value = "/prices/{productKey}/date/{date}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductPriceResponse get(
            @Valid
            @Parameter(description = "The unique identifier for a product",
                    schema = @Schema(implementation = ProductKey.class), explode = Explode.TRUE)
            @PathVariable
            ProductKey productKey,
            @Valid
            @Parameter(description = "The date on which the price is checked",
                    schema = @Schema(type = "string", format = "date-time", example = "2020-06-14T10:00:00Z"))
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @PathVariable
            LocalDateTime date
    ) {
        return RestMapper.toResponse(
                (ProductPrice) queryBus.query(new GetPriceOnDateQuery(
                        productKey.brandId(),
                        productKey.productId(),
                        date
                ))
        );
    }
}
