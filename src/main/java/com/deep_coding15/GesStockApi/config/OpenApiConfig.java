package com.deep_coding15.GesStockApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gesStockOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("GesStock API")
                .description("""
                    API REST de gestion de stock et de ventes.
                    
                    ✔ Traçabilité des mouvements  
                    ✔ Architecture modulaire  
                    ✔ Tests unitaires (JUnit 5, TDD)
                    """)
                .version("1.0.0")
                .contact(new Contact()
                    .name("Lydivine Merveille Magne Tsafack")
                    .email("tsafackmerveillem@gmail.com")
                    .url("https://github.com/deep-coding15/GesStockApi")
                    .url("https://www.linkedin.com/in/lydivine-merveille-magne-tsafack")
                )
            );
    }

}
