package com.microservice.booking.userservice.controller;

import com.microservice.booking.userservice.dataTransferObjects.UserAuthDTO;
import com.microservice.booking.userservice.entity.User;
import com.microservice.booking.userservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Optional;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")

    public ResponseEntity<?> getAll(@RequestBody UserAuthDTO userAuthDTO){
    userRepository.findAll().forEach(System.out::println);
    Optional<User> optionalUser = userRepository.findAll().stream().filter(u-> u.getUsername().equals(userAuthDTO.getUsername()) && passwordEncoder.matches(userAuthDTO.getPassword(), u.getPassword())).findFirst();
        if(!optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }
        Key key = Keys.hmacShaKeyFor("BvPHGM8C0ia4uOuxxqPD5DTbWC9F9TWvPStp3pb7ARo0oK2mJ3pd3YG4lxA9i8bj6OTbadwezxgeEByY".getBytes());

        User user = optionalUser.get();
        String token = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("id",""+user.getId())
                .claim("role", user.getRole().toString())
                .signWith(key).compact();

        return ResponseEntity.status(HttpStatus.OK).body("NEW " + user + "\n"+token);
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getNEWAll(){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NEW_AUTH");
    }


}
