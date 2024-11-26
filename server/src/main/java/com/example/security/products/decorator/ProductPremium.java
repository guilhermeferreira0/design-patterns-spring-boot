package com.example.security.products.decorator;

public class ProductPremium extends ProductDecorator {
    public ProductPremium(IProduct product) {
        super(product);
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 150;
    }
}
