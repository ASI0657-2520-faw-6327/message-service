package com.tinkuytech.nango.message.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI messageServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Message Service API")
                        .description("API para enviar mensajes a través de sockets")
                        .version("1.0"));
    }
}
