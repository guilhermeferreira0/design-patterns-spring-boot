package com.example.security.users.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.security.auth.controllers.dtos.UpdateUserDto;
import com.example.security.users.entities.User;
import com.example.security.auth.services.TokenService;
import com.example.security.users.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final TokenService tokenService;

    public UsersController(UsersService service, TokenService tokenService) {
        this.usersService = service;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<User> getUsers() {
        return this.usersService.getUsers();
    }

    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto updateUserDto,
                                        @RequestHeader(name = "Authorization") String token) throws IllegalAccessException {
        String decodedToken = this.tokenService.validateToken(token);

        if (decodedToken.isEmpty()) throw new JWTVerificationException("invalid");

        updateUserDto.setEmail(decodedToken);

        User updatedUser = this.usersService.updateUser(updateUserDto);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
