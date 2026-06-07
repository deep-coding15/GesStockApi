package com.deep_coding15.GesStockApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    private static final String BASIC_AUTH = "basicAuth";

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

                    **Authentification** : HTTP Basic Auth — cliquez sur le bouton **Authorize** \
                    et renseignez votre nom d'utilisateur et votre mot de passe.
                    """)
                .version("1.0.0")
                .contact(new Contact()
                    .name("Lydivine Merveille Magne Tsafack")
                    .email("tsafackmerveillem@gmail.com")
                    .url("https://github.com/deep-coding15/GesStockApi")
                    .url("https://www.linkedin.com/in/lydivine-merveille-magne-tsafack")
                )
            )
            .addSecurityItem(new SecurityRequirement().addList(BASIC_AUTH))
            .components(new Components()
                .addSecuritySchemes(BASIC_AUTH, new SecurityScheme()
                    .name(BASIC_AUTH)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("basic")
                    .description("Entrez votre nom d'utilisateur et votre mot de passe.")
                )
            );
    }

}
