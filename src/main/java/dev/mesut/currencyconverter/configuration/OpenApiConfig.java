package dev.mesut.currencyconverter.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Currency Conversion API Documentation")
                .version("0.0.1-SNAPSHOT")
                .description("API aims to retrieve and save Exchange Rates from external and perform " +
                        "conversion operations with these rates and save conversion log"));
    }

}
