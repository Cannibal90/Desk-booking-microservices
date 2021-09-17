package com.microservice.booking.userservice;

import com.microservice.booking.userservice.domain.Role;
import com.microservice.booking.userservice.entity.User;
import com.microservice.booking.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import web.TokenFilter;

import java.util.Collections;

@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncode() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  TokenFilter getToken() {
    return new TokenFilter();
  }

  @Bean
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

}
