package com.example.security.products.decorator;

import com.example.security.products.controllers.dtos.ProductType;

public class ProductRegular extends ProductDecorator {
    public ProductRegular(IProduct product) {
        super(product);
    }

    @Override
    public ProductType getType() {
        return ProductType.regular;
    }
}
