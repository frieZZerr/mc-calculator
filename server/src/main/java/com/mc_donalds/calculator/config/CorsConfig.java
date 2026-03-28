package com.mc_donalds.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class CorsConfig {

    private String HOSTNAME_TEMPLATE = "%s://%s:%s";

    @Value("${client.protocol}")
    private String protocol;

    @Value("${client.host}")
    private String host;

    @Value("${client.port}")
    private String port;

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        String clientHostname = String.format(HOSTNAME_TEMPLATE, protocol, host, port);

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/products/*")
                        .allowedOrigins(clientHostname)
                        .allowedMethods("GET")
                        .allowedHeaders("*");
            }
        };
    }
}
