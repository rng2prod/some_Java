package com.rngproduction.integrationCoreOW.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author e.karyagin
 */

@Configuration
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class AuthToken {
    @Value("${APIKEY}")
    private String authToken;

    public String get() {
        return authToken;
    }
}
