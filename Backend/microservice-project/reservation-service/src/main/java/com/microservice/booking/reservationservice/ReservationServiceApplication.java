package com.microservice.booking.reservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import web.TokenFilter;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class ReservationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReservationServiceApplication.class, args);
  }

  @Bean
  public RestTemplate getTemplate(){
    return new RestTemplate();
  }

  @Bean
  TokenFilter getToken(){
    return new TokenFilter();
  }

  @Bean
  public Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(
                    RequestHandlerSelectors.basePackage(
                            "com.microservice.booking.reservationservice.controller"))
            .build()
            .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
            "Reservation API",
            "Reservation Microservice API",
            "1.00",
            "Terms of service",
            new Contact("Krystian Kopeć", "URL", "222474@edu.p.lodz.pl"),
            "My licence",
            "my licence",
            Collections.emptyList());
  }
}
