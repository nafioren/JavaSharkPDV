package com.proyecto.JavaSharkPDV.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public Info apiInfo() {
        return new Info()
                .title("JavaSharkPDV API")
                .description("Documentación de la API para el proyecto PDV")
                .version("1.0.0")
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));
    }


}
