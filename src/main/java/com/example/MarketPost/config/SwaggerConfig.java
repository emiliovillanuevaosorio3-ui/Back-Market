package com.example.MarketPost.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configureOpenApi() {
        Server server = new Server()
            .url("http://localhost:8080")
            .description("Server URL in Development");

        Contact contact = new Contact()
            .name("Team Market")
            .email("https://www.google.com/");

        Info info = new Info()
            .description("Microservicio para la Gestión de Inventarios")
            .title("API REST Services")
            .version("1.0")
            .contact(contact);

        return new OpenAPI()
            .info(info)
            .servers(List.of(server));
    }
}
