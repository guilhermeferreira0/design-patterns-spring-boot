package com.example.security.payments.entities;

import com.example.security.payments.OrderBuilder;
import com.example.security.payments.factory.Payments;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    private UUID creatorId;
    private List<UUID> products;
    private Double total;
    private Payments payment;
    private String status;
    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updateTimestamp;

    public Order() {
    }

    public Order(OrderBuilder builder) {
        this.orderId = builder.getOrderId();
        this.creatorId = builder.getCreatorId();
        this.products = builder.getProducts();
        this.total = builder.getTotal();
        this.payment = builder.getPayment();
        this.status = builder.getStatus();
        this.creationTimestamp = builder.getCreationTimestamp();
        this.updateTimestamp = builder.getUpdateTimestamp();
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public List<UUID> getProducts() {
        return products;
    }

    public Double getTotal() {
        return total;
    }

    public Payments getPayment() {
        return payment;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
