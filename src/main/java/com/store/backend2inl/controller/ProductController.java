package com.store.backend2inl.controller;

import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import com.store.backend2inl.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO: Gör om till Controller för Thymeleaf
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    ApiService apiService;

    public ProductController(ProductRepository productRepository, ApiService apiService) {
        this.productRepository = productRepository;
        this.apiService = apiService;
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<List<Product>> fetchProducts() throws Exception {
        apiService.fetchAndSaveProducts();
        return ResponseEntity.ok().body(productRepository.findAll());
    }
}