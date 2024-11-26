package com.example.security.payments.services;

import com.example.security.payments.OrderBuilder;
import com.example.security.payments.PaymentsRepository;
import com.example.security.payments.entities.Order;
import com.example.security.payments.factory.PaymentConstructor;
import com.example.security.payments.factory.Payments;
import com.example.security.products.entities.Product;
import com.example.security.users.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentsService {
    private final PaymentsRepository paymentsRepository;

    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    public List<Order> getOrder(User user) {
        List<Order> orders = this.paymentsRepository.findByCreatorId(user.getUserId());
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }

        return orders;
    }

    public void addProduct(User user, Product product) {
        List<Order> existingOrders = this.paymentsRepository.findByCreatorId(user.getUserId());

        if (existingOrders == null || existingOrders.isEmpty()) {
            OrderBuilder orderBuilder = new OrderBuilder();
            orderBuilder.setOrderId(UUID.randomUUID());
            orderBuilder.setPayment(Payments.credit);
            orderBuilder.setStatus("Processing...");
            orderBuilder.setCreationTimestamp(Instant.now());
            orderBuilder.setUpdateTimestamp(null);
            orderBuilder.setCreatorId(user.getUserId());
            orderBuilder.setProducts(new ArrayList<>());
            orderBuilder.setTotal(product.getPrice());
            Order newOrder = orderBuilder.builder();

            newOrder.getProducts().add(product.getProductId());

            this.paymentsRepository.save(newOrder);
        } else {
            Order orderUpdated = existingOrders.getFirst();
            orderUpdated.getProducts().add(product.getProductId());
            orderUpdated.setTotal(orderUpdated.getTotal() + product.getPrice());

            this.paymentsRepository.save(orderUpdated);
        }
    }

    public Order purchaseProducts(User user, PaymentConstructor payment) {
        List<Order> existingOrders = this.paymentsRepository.findByCreatorId(user.getUserId());
        if (existingOrders == null || existingOrders.isEmpty()) {
            throw new IllegalArgumentException("Order undefined");
        }

        Order orderUpdated = existingOrders.getFirst();

        orderUpdated.setTotal(orderUpdated.getTotal() + payment.getValue());
        orderUpdated.setPayment(payment.getPaymentType());

        return orderUpdated;
    }

    @Transactional
    public void deletePayment(User user) {
        this.paymentsRepository.deleteByCreatorId(user.getUserId());
    }
}
