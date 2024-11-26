package com.example.security.auth.controllers;

import com.example.security.auth.services.AuthenticateService;
import com.example.security.auth.controllers.dtos.CreateUserDto;
import com.example.security.auth.controllers.dtos.LoginUserDto;
import com.example.security.ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticateService authenticateService;

    public AuthController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @NotNull CreateUserDto createUserDto) {
        try {
            Object userResponse = this.authenticateService.authenticate(createUserDto);

            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

        } catch (IllegalArgumentException error) {
            ErrorResponse errorResponse = new ErrorResponse(
                    LocalDateTime.now(),
                    "Empty fields",
                    error.getMessage(),
                    "/auth/register"
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @NotNull LoginUserDto loginUserDto) {
        try {
            Object userResponse = this.authenticateService.authorization(loginUserDto);

            return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException error) {
            ErrorResponse errorResponse = new ErrorResponse(
                    LocalDateTime.now(),
                    "Unauthorized",
                    error.getMessage(),
                    "/auth/login"
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getUserLogged(@RequestHeader("Authorization") String token) {
        Object response = this.authenticateService.getUserLogged(token);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
