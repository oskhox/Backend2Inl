package com.store.backend2inl.service;


import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    ProductRepository productRepository;
    public ApiService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        fetchAndSaveProducts();
    }

    public void fetchAndSaveProducts() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakestoreapi.com/products";
        try {
            ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
            Product[] products = response.getBody();
            if (products != null) {
                for (Product p : products) {
                    productRepository.save(p);
                    System.out.println("Saved product " + p.getTitle());
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem saving the products");
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}