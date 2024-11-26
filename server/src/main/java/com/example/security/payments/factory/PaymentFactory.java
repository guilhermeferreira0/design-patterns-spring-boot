package com.example.security.payments.factory;

import com.example.security.payments.services.PaymentsService;

public class PaymentFactory {

    private final PaymentsService paymentsService;

    public PaymentFactory(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    public PaymentMethod createPayment(Payments paymentType) {
        if (paymentType == null) throw new IllegalArgumentException("Payment null");

        return switch (paymentType) {
            case pix -> new PixPayment(paymentsService);
            case paypal -> new PaypalPayment(paymentsService);
            case debit -> new DebitCardPayment(paymentsService);
            default -> new CreditCardPayment(paymentsService);
        };
    }
}
