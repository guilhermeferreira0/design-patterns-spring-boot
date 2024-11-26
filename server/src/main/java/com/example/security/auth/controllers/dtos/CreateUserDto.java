package com.example.security.auth.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserDto extends UserDto {
    private final String username;

    public CreateUserDto(String name, String email, String password) {
        super(email, password);
        this.username = name;
    }

    public String getUsername() {
        return username;
    }
}

