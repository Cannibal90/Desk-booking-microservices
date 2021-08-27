package com.microservice.booking.laboratoryroomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LaboratoryRoomServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaboratoryRoomServiceApplication.class, args);
  }
}
