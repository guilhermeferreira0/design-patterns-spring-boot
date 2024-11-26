package com.example.security.users.services;

import com.example.security.users.UserBuilder;
import com.example.security.users.UsersRepository;
import com.example.security.auth.controllers.dtos.CreateUserDto;
import com.example.security.auth.controllers.dtos.UpdateUserDto;
import com.example.security.auth.controllers.dtos.UserDto;
import com.example.security.users.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository repository) {
        this.usersRepository = repository;
    }

    public UUID createUser(@NotNull CreateUserDto createUserDto) {
        // DTO => ENTITY
        UserBuilder entity = new UserBuilder();
        entity.setUserId(UUID.randomUUID());
        entity.setUsername(createUserDto.getUsername());
        entity.setEmail(createUserDto.getEmail());
        entity.setPassword(createUserDto.getPassword());
        entity.setCreationTimestamp(Instant.now());
        entity.setUpdateTimestamp(null);

        User newUser = entity.build();

        User userSaved = this.usersRepository.save(newUser);
        return userSaved.getUserId();
    }

    public List<User> getUsers() {
        return this.usersRepository.findAll();
    }

    public Object getUser(@NotNull UserDto userDto) {
        Optional<User> existingUser = this.usersRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            return existingUser.get();
        };

        throw new IllegalArgumentException("User Not found");
    }

    public User updateUser(UpdateUserDto updateUserDto) throws IllegalAccessException {
        Object existingUser = this.getUser(updateUserDto);

        User user = ((User) existingUser);

        BeanUtils.copyProperties(updateUserDto, user);

        this.usersRepository.save(user);

        return user;
    }
}
