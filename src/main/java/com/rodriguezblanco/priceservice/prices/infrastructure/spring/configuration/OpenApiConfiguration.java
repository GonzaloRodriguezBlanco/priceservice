package com.rodriguezblanco.priceservice.prices.infrastructure.spring.configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class OpenApiConfiguration {
    /*
    // https://springdoc.org/#what-is-a-proper-way-to-set-up-swagger-ui-to-use-provided-spec-yml
    @Bean
    SpringDocConfiguration springDocConfiguration(){
        return new SpringDocConfiguration();
    }

    @Bean
    SpringDocConfigProperties springDocConfigProperties() {
        return new SpringDocConfigProperties();
    }

    @Bean
    ObjectMapperProvider objectMapperProvider(SpringDocConfigProperties springDocConfigProperties){
        return new ObjectMapperProvider(springDocConfigProperties);
    }*/

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
