package com.example.security.products.decorator;

import com.example.security.products.controllers.dtos.ProductType;

import java.util.UUID;

public interface IProduct {
    String getName();
    Double getPrice();
    String getDescription();
    UUID getCreatorId();
    ProductType getType();
    Boolean getGuarantee();
}
