package com.example.security.payments.controllers.dtos;

import com.example.security.payments.factory.Payments;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Payments payment;

    public OrderDto() {
    }

    public OrderDto(Payments payment) {
        this.payment = payment;
    }

    public Payments getPayment() {
        return payment;
    }
}
