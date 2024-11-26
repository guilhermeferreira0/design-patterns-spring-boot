package com.example.security.products.decorator;

import com.example.security.products.controllers.dtos.ProductType;

import java.util.UUID;

public abstract class ProductDecorator implements IProduct {
    private IProduct product;

    public ProductDecorator(IProduct product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return this.product.getName();
    }

    @Override
    public Double getPrice() {
        return this.product.getPrice();
    }

    @Override
    public String getDescription() {
        return this.product.getDescription();
    }

    @Override
    public UUID getCreatorId() {
        return this.product.getCreatorId();
    }

    @Override
    public ProductType getType() {
        return this.product.getType();
    }

    @Override
    public Boolean getGuarantee() {
        return this.product.getGuarantee();
    }
}
