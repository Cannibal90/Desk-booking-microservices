package com.microservice.booking.laboratoryroomservice.configuration;

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
  public static final String LABORATORY_TAG = "Laboratory API";
  public static final String DESK_TAG = "Desk API";
  public static final String STATION_TAG = "Computer station API";

  @Bean
  public Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(
            RequestHandlerSelectors.basePackage(
                "com.microservice.booking.laboratoryroomservice.controller"))
        .build()
        .tags(
            new Tag(LABORATORY_TAG, "Laboratory API"),
            new Tag(DESK_TAG, "Desk API"),
            new Tag(STATION_TAG, "Computer station  API"))
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
        "Laboratory API",
        "Laboratory Microservice API",
        "1.00",
        "Terms of service",
        new Contact("Krystian Kopeć", "URL", "222474@edu.p.lodz.pl"),
        "My licence",
        "my licence",
        Collections.emptyList());
  }
}
