package com.example.security.products.controllers.dtos;

import com.example.security.products.decorator.IProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;



@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto implements IProduct {
    private String name;
    private String description;
    private Double price;
    private UUID creatorId;
    private ProductType type;
    private Boolean guarantee;

    public ProductDto(String name, String description, Double price, ProductType type, Boolean guarantee) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.guarantee = guarantee;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public UUID getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(UUID userId) {
        this.creatorId = userId;
    }

    public ProductType getType() {
        return this.type;
    }

    public void setTypeNull() {
        this.type = ProductType.regular;
    }

    public Boolean getGuarantee() {
        return this.guarantee;
    }
}
