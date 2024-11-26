package com.example.security.auth.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.security.ErrorResponse;
import com.example.security.auth.controllers.dtos.CreateUserDto;
import com.example.security.auth.controllers.dtos.LoginUserDto;
import com.example.security.auth.controllers.dtos.UpdateUserDto;
import com.example.security.users.entities.User;
import com.example.security.users.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthenticateService {

    private final UsersService usersService;
    private final TokenService tokenService;

    public AuthenticateService(UsersService usersService, TokenService tokenService) {
        this.usersService = usersService;
        this.tokenService = tokenService;
    }

    public Object authenticate(CreateUserDto createUserDto) {
        boolean userDtoIsValid = createUserDto.getUsername() != null && !createUserDto.getUsername().isEmpty() &&
                createUserDto.getEmail() != null && !createUserDto.getEmail().isEmpty() &&
                createUserDto.getPassword() != null && !createUserDto.getPassword().isEmpty();

        if (!userDtoIsValid) throw new IllegalArgumentException("Fill in all fields");

        UUID userId = this.usersService.createUser(createUserDto);
        String token = tokenService.generateToken(userId, createUserDto);

        return Map.of("userId", userId.toString(), "email", createUserDto.getEmail(), "token", token);
    }

    public Object authorization(LoginUserDto loginUserDto) {
        boolean loginDtoIsValid = loginUserDto.getEmail() != null && !loginUserDto.getEmail().isEmpty() &&
                loginUserDto.getPassword() != null && !loginUserDto.getPassword().isEmpty();

        if (!loginDtoIsValid) throw new IllegalArgumentException("Invalid Fields");

        Object existingUser = this.usersService.getUser(loginUserDto);

        if (Objects.equals(((User) existingUser).getPassword(), loginUserDto.getPassword())) {
            String token = tokenService.generateToken(((User) existingUser).getUserId(), loginUserDto);

            return Map.of("userId", ((User) existingUser).getUserId(), "email", loginUserDto.getEmail(), "token", token);
        }

        throw new IllegalArgumentException("User Not Authorized");
    }

    public Object getUserLogged(String token) {
        try {
            String decodedToken = this.tokenService.validateToken(token);

            UpdateUserDto instanceUser  = new UpdateUserDto();
            instanceUser.setEmail(decodedToken);

            return this.usersService.getUser(instanceUser);

        } catch (JWTVerificationException | IllegalArgumentException error) {
            ErrorResponse response = new ErrorResponse(
                    LocalDateTime.now(),
                    "Auth Failed",
                    error.getMessage(),
                    "/auth"
            );

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
