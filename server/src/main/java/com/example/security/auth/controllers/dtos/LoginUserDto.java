package com.example.security.auth.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUserDto extends UserDto {
    public LoginUserDto(String email, String password) {
        super(email, password);
    }
}
