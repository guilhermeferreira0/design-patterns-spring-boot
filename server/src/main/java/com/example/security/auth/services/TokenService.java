package com.example.security.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.security.auth.controllers.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class TokenService {
    private static final String JWT_SECRET = "secret";

    public String generateToken(UUID userId, UserDto userDto) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject(userDto.getEmail())
                .withClaim("userId", userId.toString())
                .withClaim("email", userDto.getEmail())
                .withIssuedAt(Instant.now())
                .withIssuer("SecurityApp")
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }

    public String validateToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .withIssuer("SecurityApp")
                .build();

        DecodedJWT decoded = verifier.verify(token);

        return decoded.getClaim("email").asString();
    }
}
