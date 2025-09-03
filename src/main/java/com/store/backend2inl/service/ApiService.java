package com.store.backend2inl.service;

import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

                    Product productObj = new Product();
                    productObj.setTitle(p.getTitle());
                    productObj.setPrice(p.getPrice());
                    productObj.setDescription(p.getDescription());
                    productObj.setCategory(p.getCategory());
                    productObj.setImage(p.getImage());
                    productObj.setRating(p.getRating());
                    productRepository.save(productObj);
                    System.out.println("Saved product " + productObj.getTitle());
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem saving the products");
            e.printStackTrace();
        }
    }
}