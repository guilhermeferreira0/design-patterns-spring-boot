package com.example.security.products.decorator;

public class ProductGuarantee extends ProductDecorator {
    public ProductGuarantee(IProduct product) {
        super(product);
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 70;
    }
}
