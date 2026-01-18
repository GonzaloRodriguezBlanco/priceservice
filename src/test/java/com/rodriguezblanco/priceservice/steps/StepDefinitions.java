package com.rodriguezblanco.priceservice.steps;

import com.rodriguezblanco.priceservice.AcceptanceTest;
import com.rodriguezblanco.priceservice.prices.domain.ProductPriceRepository;
import com.rodriguezblanco.priceservice.prices.domain.entity.ProductPrice;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.Currency;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.Period;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.ProductPriceIdentity;
import com.rodriguezblanco.priceservice.prices.domain.valueobject.SellingPrice;
import com.rodriguezblanco.priceservice.prices.user.rest.response.ProductPriceResponse;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(
        classes = AcceptanceTest.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureRestTestClient
public class StepDefinitions {
    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    private ProductPriceRepository priceRepository;

    @Autowired
    private FeatureState featureState;

    @DataTableType
    public ProductPrice priceEntryTransformer(Map<String, String> entry) {
        return ProductPrice.create(
                ProductPriceIdentity.of(
                        Integer.valueOf(entry.get("brand_id")),
                        Long.valueOf(entry.get("product_id")),
                        Integer.valueOf(entry.get("price_list"))
                ),
                Period.of(
                        LocalDateTime.parse(entry.get("start_date")),
                        LocalDateTime.parse(entry.get("end_date"))
                ),
                Integer.valueOf(entry.get("priority")),
                SellingPrice.of(
                        Double.valueOf(entry.get("price")),
                        Currency.valueOf(entry.get("curr"))
                )
        );
    }

    @Given("the following prices exists:")
    public void the_following_prices_exists(List<ProductPrice> productPrices) {
        productPrices.forEach(price -> this.priceRepository.save(price));
    }

    @When("query the price for product {long} and brand {int} on date {string}")
    public void query_the_price_for_product_and_brand_on_date(Long productId, Integer brandId, String date) {
        this.featureState.setPrice(restTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/prices/productId=" + productId + ",brandId=" + brandId)
                        .path("/date/" + HtmlUtils.htmlEscape(date))
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(ProductPriceResponse.class)
                .getResponseBody()
        );
    }

    @Then("the price is {double}")
    public void the_price_is(Double expectedPrice) {
        assertEquals(expectedPrice, this.featureState.price().sellingPrice());
    }
}
