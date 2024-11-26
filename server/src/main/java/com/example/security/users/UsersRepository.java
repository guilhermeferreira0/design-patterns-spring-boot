package com.example.security.users;

import com.example.security.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
