package com.rodriguezblanco.priceservice.unit;

import com.rodriguezblanco.priceservice.prices.application.GetPriceOnDateQuery;
import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.Currency;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.Period;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.ProductPriceIdentity;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.SellingPrice;
import com.rodriguezblanco.priceservice.prices.user.rest.GetProductPriceInDateController;
import com.rodriguezblanco.priceservice.prices.user.rest.request.ProductKey;
import com.rodriguezblanco.priceservice.support.application.query.Query;
import com.rodriguezblanco.priceservice.support.application.userports.QueryBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetProductPriceInDateController.class)
@Tag("unit")
public class GetProductPriceInDateControllerITest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    QueryBus<Query> queryBus;

    private ProductKey productKey;
    private String date;
    private GetPriceOnDateQuery query;
    private ProductPrice productPrice;

    @BeforeEach
    void setup() {
        Long productId = 35455L;
        Short brandId = 1;
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59");
        Short priceList = 1;
        Short priority = 0;
        Double price = 35.50;
        Currency currency = Currency.EUR;

        productKey = new ProductKey(productId, Integer.valueOf(brandId));
        date = "2020-06-14T10:00:00";
        query = new GetPriceOnDateQuery(productKey.brandId(), productKey.productId(), LocalDateTime.parse(date));
        productPrice = ProductPrice.create(
                ProductPriceIdentity.of(
                        brandId,
                        productId,
                        priceList
                ),
                Period.of(
                        startDate,
                        endDate
                ),
                priority,
                SellingPrice.of(
                        price,
                        currency
                )
        );
        given(queryBus.query(query)).willReturn(productPrice);
    }

    @Test
    void when_perform_get_with_existing_product_should_success() throws Exception {

        mockMvc.perform(
                    get("/api/prices/{productKey}/date/{date}", productKey, date)
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"productId\":35455,\"brandId\":1,\"priceList\":1,\"from\":\"2020-06-14T00:00:00\",\"to\":\"2020-12-31T23:59:59\",\"sellingPrice\":35.5,\"currency\":\"EUR\"}"));

        verify(queryBus, times(1)).query(query);
    }
}
