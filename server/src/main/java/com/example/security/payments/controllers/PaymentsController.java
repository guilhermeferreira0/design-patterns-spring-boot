package com.example.security.payments.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.security.auth.controllers.dtos.UpdateUserDto;
import com.example.security.auth.services.TokenService;
import com.example.security.payments.controllers.dtos.OrderDto;
import com.example.security.payments.entities.Order;
import com.example.security.payments.factory.PaymentFactory;
import com.example.security.payments.factory.PaymentMethod;
import com.example.security.payments.services.PaymentsService;
import com.example.security.products.entities.Product;
import com.example.security.products.services.ProductService;
import com.example.security.users.entities.User;
import com.example.security.users.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;
    private final TokenService tokenService;
    private final UsersService usersService;
    private final ProductService productService;

    public PaymentsController(PaymentsService paymentsService, TokenService tokenService, UsersService usersService, ProductService productService) {
        this.paymentsService = paymentsService;
        this.tokenService = tokenService;
        this.usersService = usersService;
        this.productService = productService;
    }

    private User getUserIdFromToken(String token) {
        try {
            String decodedToken = tokenService.validateToken(token);
            UpdateUserDto userDto = new UpdateUserDto();
            userDto.setEmail(decodedToken);

            return (User) usersService.getUser(userDto);
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token validation failed", e);
        }
    }

    @GetMapping
    public List<Order> getOrder(@RequestHeader("Authorization") String token) {
        User decodedUser = getUserIdFromToken(token);

        return this.paymentsService.getOrder(decodedUser);
    }

    @GetMapping(path = "{productId}")
    public Object addProduct(@RequestHeader("Authorization") String token,
                             @PathVariable("productId") String productId) {
        User decodedUser = getUserIdFromToken(token);
        Optional<Product> existingProduct = this.productService.getProductsById(UUID.fromString(productId));

        if (existingProduct.isEmpty()) {
            return Map.of("message", "Product Undefined");
        }

        existingProduct.ifPresent(product -> {
            this.paymentsService.addProduct(decodedUser, product);
        });

        return Map.of("message", "Product accepted");
    }

    @PostMapping
    public Order finalizePayment(@RequestHeader("Authorization") String token,
                                  @RequestBody OrderDto orderDto) {
        User decodedUser = getUserIdFromToken(token);

        PaymentFactory paymentFactory = new PaymentFactory(this.paymentsService);
        PaymentMethod paymentMethod = paymentFactory.createPayment(orderDto.getPayment());
        return paymentMethod.processPayment(decodedUser);
    }

    @DeleteMapping
    public Object deletePayment(@RequestHeader("Authorization") String token) {
        User decodedUser = getUserIdFromToken(token);

        this.paymentsService.deletePayment(decodedUser);

        return Map.of("message", "Payment Deleted");
    }
}
