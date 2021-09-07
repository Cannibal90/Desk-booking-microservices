package com.microservice.booking.reservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import web.TokenFilter;

@SpringBootApplication
@EnableEurekaClient
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
}
