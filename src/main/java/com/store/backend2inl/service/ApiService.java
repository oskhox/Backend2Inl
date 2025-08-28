package com.store.backend2inl.service;


import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiService {

    ProductRepository productRepository;
    public ApiService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
}