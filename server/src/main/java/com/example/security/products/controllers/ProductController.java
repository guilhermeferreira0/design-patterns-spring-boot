package com.example.security.products.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.security.products.controllers.dtos.ProductDto;
import com.example.security.auth.controllers.dtos.UpdateUserDto;
import com.example.security.products.decorator.*;
import com.example.security.products.entities.Product;
import com.example.security.users.entities.User;
import com.example.security.products.services.ProductService;
import com.example.security.auth.services.TokenService;
import com.example.security.users.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final TokenService tokenService;
    private final UsersService usersService;
    private final ProductService productService;

    public ProductController(TokenService tokenService, UsersService usersService, ProductService productService) {
        this.tokenService = tokenService;
        this.usersService = usersService;
        this.productService = productService;
    }

    private UUID getUserIdFromToken(String token) {
        try {
            String decodedToken = tokenService.validateToken(token);
            UpdateUserDto userDto = new UpdateUserDto();
            userDto.setEmail(decodedToken);

            User existingUser = (User) usersService.getUser(userDto);

            return existingUser.getUserId();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token validation failed", e);
        }
    }

    private IProduct createProductByType(IProduct product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        return switch (product.getType()) {
            case premium -> new ProductPremium(product);
            case limitedEdition -> new ProductLimitedEdition(product);
            default -> {
                yield product;
            }
        };
    }

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestHeader("Authorization") String token,
                                             @RequestBody ProductDto productDto) {
        UUID userId = this.getUserIdFromToken(token);
        productDto.setCreatorId(userId);
        IProduct product = productDto;

        if (productDto.getGuarantee()) product = new ProductGuarantee(productDto);

        IProduct productDecorator = createProductByType(product);

        Product newProduct = this.productService.registerProduct(productDecorator);

        return ResponseEntity.ok(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(this.productService.getProducts());
    }
}
