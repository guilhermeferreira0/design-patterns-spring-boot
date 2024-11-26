package com.example.security.products.entities;

import com.example.security.products.ProductBuilder;
import com.example.security.products.controllers.dtos.ProductType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;
    private String name;
    private String description;
    private Double price;
    private UUID creatorId;
    private ProductType type;
    private Boolean guarantee;
    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updateTimestamp;

    public Product() {
    }

    public Product(ProductBuilder builder) {
        this.name = builder.getName();
        this.description = builder.getDescription();
        this.price = builder.getPrice();
        this.productId = builder.getProductId();
        this.creatorId = builder.getCreatorId();
        this.type = builder.getType();
        this.guarantee = builder.getGuarantee();
        this.creationTimestamp = builder.getCreationTimestamp();
        this.updateTimestamp = builder.getUpdateTimestamp();
    }

    public UUID getCreatedId() {
        return creatorId;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public ProductType getType() {
        return type;
    }

    public Boolean getGuarantee() {
        return guarantee;
    }
}
