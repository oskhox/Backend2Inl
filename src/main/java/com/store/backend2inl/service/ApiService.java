package com.store.backend2inl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ApiService {

    ProductRepository productRepository;
    public ApiService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void fetchAndSaveProducts() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("https://fakestoreapi.com/products");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

            Product[] products = mapper.readValue(sb.toString(), Product[].class);

            for (Product p : products) {
                productRepository.save(p);
                System.out.println("Saved product: " + p.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}