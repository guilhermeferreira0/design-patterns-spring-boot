package com.example.security.payments.factory;

import com.example.security.payments.services.PaymentsService;

public class PixPayment extends PaymentConstructor {

    private final double value = (-50.0);
    private final Payments paymentType = Payments.pix;

    public PixPayment(PaymentsService paymentsService) {
        super(paymentsService);
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
