package com.rodriguezblanco.priceservice.integration;

import com.rodriguezblanco.priceservice.prices.application.GetPriceOnDateQuery;
import com.rodriguezblanco.priceservice.prices.application.GetPriceOnDateQueryHandler;
import com.rodriguezblanco.priceservice.prices.domain.ProductPriceRepository;
import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.support.application.query.Query;
import com.rodriguezblanco.priceservice.support.application.query.QueryHandler;
import com.rodriguezblanco.priceservice.support.application.userports.QueryBus;
import com.rodriguezblanco.priceservice.support.user.bus.NoHandlerForMessage;
import com.rodriguezblanco.priceservice.support.user.bus.QueryBusImpl;
import com.rodriguezblanco.priceservice.unit.Mother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Tag("integration")
public class QueryBusITest {

    @MockitoBean
    ProductPriceRepository repository;

    private GetPriceOnDateQueryHandler handler;

    private QueryBus<Query> queryBus;

    ProductPrice expectedProductPrice;

    @BeforeEach
    void setup() {
        expectedProductPrice = Mother.defaultProductPrice();

        handler = new GetPriceOnDateQueryHandler(repository);

        queryBus = new QueryBusImpl();
        given(repository.findProductPriceInDate(
                Mother.defaultBrandId(),
                Mother.defaultProductId(),
                Mother.defaultQueryDate())
        ).willReturn(expectedProductPrice);
    }

    @Test void when_send_message_registered_query_handler_should_receive_messages() {
        // Arrange
        queryBus.register((QueryHandler) handler);
        GetPriceOnDateQuery query = new GetPriceOnDateQuery(
                Mother.defaultBrandId(),
                Mother.defaultProductId(),
                Mother.defaultQueryDate()
        );

        // Act
        Object result = this.queryBus.query(query);

        // Assert
        assertEquals(expectedProductPrice, result);
    }

    @Test void when_send_message_unregistered_query_handler_should_fail() {
        GetPriceOnDateQuery query = new GetPriceOnDateQuery(
                Mother.defaultBrandId(),
                Mother.defaultProductId(),
                Mother.defaultQueryDate()
        );

        Exception exception = assertThrows(NoHandlerForMessage.class, () -> {
            this.queryBus.query(query);
        });

        assertTrue(exception.getMessage().contains("No handler found for type"));
    }

}
