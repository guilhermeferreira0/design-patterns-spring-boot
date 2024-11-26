package com.example.security.auth.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserDto extends UserDto {
    private String username;

    public UpdateUserDto() {
        super(null, null);
    }

    public UpdateUserDto(String username, String email, String password
    ) {
        super(email, password);
        this.username = username;
    }

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
