package com.microservice.booking.userservice.repository;

import com.microservice.booking.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
