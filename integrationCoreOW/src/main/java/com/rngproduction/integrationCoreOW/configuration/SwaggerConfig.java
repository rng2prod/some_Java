package com.rngproduction.integrationCoreOW.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Конфигурация Swagger
 */

@OpenAPIDefinition(
        info = @Info(
                title = "integrationCoreOW",
                description = "API системы ядро интеграции OW",
                version = "1.0.0",
                contact = @Contact(
                        name = "e.karyagin",
                        email = "rng2prod@gmail.com"
                )
        )
)
@SecurityScheme(
        name = "ApiKey",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "API-Key",
        description = "API Key authentication"
)
public class SwaggerConfig {
}
