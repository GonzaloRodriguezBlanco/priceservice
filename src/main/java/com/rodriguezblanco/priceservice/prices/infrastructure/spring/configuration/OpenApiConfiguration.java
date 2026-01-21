package com.rodriguezblanco.priceservice.prices.infrastructure.spring.configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class OpenApiConfiguration {

    @Bean

    public OpenAPI springShopOpenAPI(@Value("${application.name}") String applicationName, @Value("${application.description}") String description, @Value("${application.version}") String version) {
        return new OpenAPI()
                .info(new Info().title(applicationName + " API")
                        .description(description)
                        .version("v" + version)
                        .license(new License().name("Proprietary")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub")
                        .url("https://github.com/GonzaloRodriguezBlanco/priceservice"));
    }
}
