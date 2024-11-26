package com.example.security.products.decorator;

public class ProductLimitedEdition extends ProductDecorator {
    public ProductLimitedEdition(IProduct product) {
        super(product);
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 100;
    }
}
