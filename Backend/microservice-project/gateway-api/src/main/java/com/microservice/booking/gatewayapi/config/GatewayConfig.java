package com.microservice.booking.gatewayapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("laboratory-service", r -> r.path("/laboratory/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://LABORATORY-SERVICE"))

                .route("reservation-service", r -> r.path("/reservation/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://RESERVATION-SERVICE"))

                .route("user-service", r -> r.path("/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE"))
                .build();
    }
}
