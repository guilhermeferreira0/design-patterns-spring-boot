package com.example.security.payments.factory;

import com.example.security.payments.services.PaymentsService;

public class PaypalPayment extends PaymentConstructor {

    private final double value = 120.0;
    private final Payments paymentType = Payments.paypal;

    public PaypalPayment(PaymentsService paymentsService) {
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
