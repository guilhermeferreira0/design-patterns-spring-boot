package com.example.security.products.services;

import com.example.security.products.ProductBuilder;
import com.example.security.products.decorator.IProduct;
import com.example.security.products.ProductsRepository;
import com.example.security.products.entities.Product;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getProducts() {
        return this.productsRepository.findAll();
    }

    public Product registerProduct(IProduct product) {
        ProductBuilder productBuilder = new ProductBuilder();
        productBuilder.setProductId(UUID.randomUUID());
        productBuilder.setCreatorId(product.getCreatorId());
        productBuilder.setCreationTimestamp(Instant.now());
        productBuilder.setUpdateTimestamp(null);
        productBuilder.setName(product.getName());
        productBuilder.setDescription(product.getDescription());
        productBuilder.setPrice(product.getPrice());
        productBuilder.setType(product.getType());
        productBuilder.setGuarantee(product.getGuarantee());

        Product newProduct = productBuilder.builder();

        return this.productsRepository.save(newProduct);
    }

    public Optional<Product> getProductsById(UUID productId) {
        return this.productsRepository.findById(productId);
    }
}
