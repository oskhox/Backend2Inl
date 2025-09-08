package com.store.backend2inl.service;

import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produkten hittades ej"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}