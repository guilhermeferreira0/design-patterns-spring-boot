package com.example.security.payments.factory;

import com.example.security.payments.entities.Order;
import com.example.security.users.entities.User;

public interface PaymentMethod {
    Order processPayment(User user);
    Payments getPaymentType();
    double getValue();
}
