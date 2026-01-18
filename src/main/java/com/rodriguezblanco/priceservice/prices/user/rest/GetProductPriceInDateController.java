package com.rodriguezblanco.priceservice.prices.user.rest;

import com.rodriguezblanco.priceservice.prices.application.GetPriceOnDateQuery;
import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.user.rest.request.ProductKey;
import com.rodriguezblanco.priceservice.prices.user.rest.response.ProductPriceResponse;
import com.rodriguezblanco.priceservice.support.application.query.Query;
import com.rodriguezblanco.priceservice.support.application.userports.QueryBus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class GetProductPriceInDateController {

    final QueryBus<Query> queryBus;

    public GetProductPriceInDateController(QueryBus<Query> queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping(
            value = "/prices/{productKey}/date/{date}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductPriceResponse get(
            @PathVariable ProductKey productKey,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime date
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
