package com.rngproduction.integrationCoreOW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * @author e.karyagin
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class IntegrationCoreOWApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationCoreOWApplication.class, args);
    }
}
