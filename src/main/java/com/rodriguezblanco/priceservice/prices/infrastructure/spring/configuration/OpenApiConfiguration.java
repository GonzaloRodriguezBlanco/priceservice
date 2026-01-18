package com.rodriguezblanco.priceservice.prices.infrastructure.spring.configuration;


import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class OpenApiConfiguration {
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
    }
}
