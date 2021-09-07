package com.microservice.booking.laboratoryroomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import web.TokenFilter;

@SpringBootApplication
@EnableEurekaClient
public class LaboratoryRoomServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaboratoryRoomServiceApplication.class, args);
  }

  @Bean
  TokenFilter getToken(){
    return new TokenFilter();
  }
}
