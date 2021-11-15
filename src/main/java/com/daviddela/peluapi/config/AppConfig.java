package com.daviddela.peluapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(
        basePackages = {
                "com.daviddela.peluapi.domain"
        }
)
@EnableJpaRepositories(
        basePackages = {
                "com.daviddela.peluapi.repository"
        }
)
@Configuration
public class AppConfig {
    @Value("${spring.profiles.active:dev}")
    private String activeProfiles;
}
