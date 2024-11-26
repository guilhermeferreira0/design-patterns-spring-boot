package com.example.security.payments.factory;

import com.example.security.payments.entities.Order;
import com.example.security.payments.services.PaymentsService;
import com.example.security.users.entities.User;

public abstract class PaymentConstructor implements PaymentMethod {

    private final double value = 0.0;
    private final Payments paymentType = Payments.credit;
    protected final PaymentsService paymentsService;

    public PaymentConstructor(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @Override
    public Order processPayment(User user) {
        return this.paymentsService.purchaseProducts(user, this);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Payments getPaymentType() {
        return paymentType;
    }
}
