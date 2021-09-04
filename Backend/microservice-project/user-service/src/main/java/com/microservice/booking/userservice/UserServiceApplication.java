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

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncode(){
    return new BCryptPasswordEncoder();
  }

  @Autowired
  UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {

    User user = new User("admin", passwordEncode().encode("admin"), "admin@gmail.com", Role.ROLE_ADMIN);
    userRepository.save(user);
  }
}
