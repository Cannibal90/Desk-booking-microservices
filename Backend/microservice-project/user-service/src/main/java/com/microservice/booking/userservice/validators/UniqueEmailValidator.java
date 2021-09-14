package com.microservice.booking.userservice.validators;

import com.microservice.booking.userservice.entity.User;
import com.microservice.booking.userservice.repository.UserRepository;
import com.microservice.booking.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findUserByEmail(s).isEmpty();
    }
}
