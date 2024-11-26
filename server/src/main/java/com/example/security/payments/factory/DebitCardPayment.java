package com.example.security.payments.factory;

import com.example.security.payments.services.PaymentsService;

public class DebitCardPayment extends PaymentConstructor {

    private final double value = (-30.0);
    private final Payments paymentType = Payments.debit;

    public DebitCardPayment(PaymentsService paymentsService) {
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
