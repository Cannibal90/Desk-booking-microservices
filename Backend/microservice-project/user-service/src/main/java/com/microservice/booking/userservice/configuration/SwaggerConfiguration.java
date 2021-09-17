package com.microservice.booking.userservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {
    public static final String USER_TAG = "User API";

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(
                        RequestHandlerSelectors.basePackage(
                                "com.microservice.booking.userservice.controller"))
                .build()
                .tags(new Tag(USER_TAG, "User microservice API" ))
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "User API",
                "User Microservice API",
                "1.00",
                "Terms of service",
                new Contact("Krystian Kopeć", "URL", "222474@edu.p.lodz.pl"),
                "My licence",
                "my licence",
                Collections.emptyList());
    }
}
