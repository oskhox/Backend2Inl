package com.store.backend2inl;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTestIT {

    String url = "https://fakestoreapi.com/products";
    RestTemplate restTemplate = new RestTemplate();
    String json = restTemplate.getForObject(url, String.class);

    @Test
    void shouldReturnId() {
        System.out.println("Testing if it contains id");
        assertTrue(json.contains("id"));
    }

    @Test
    void shouldReturnTitle() {
        System.out.println("Testing if it contains title");
        assertTrue(json.contains("title"));
    }

    @Test
    void shouldReturnPrice() {
        System.out.println("Testing if it contains price");
        assertTrue(json.contains("price"));
    }

    @Test
    void shouldReturnDescription() {
        System.out.println("Testing if it contains description");
        assertTrue(json.contains("description"));
    }

    @Test
    void shouldReturnCategory() {
        System.out.println("Testing if it contains category");
        assertTrue(json.contains("category"));
    }

    @Test
    void shouldReturnImage() {
        System.out.println("Testing if it contains image");
        assertTrue(json.contains("image"));
    }

    @Test
    void shouldReturnRating() {
        System.out.println("Testing if it contains rating");
        assertTrue(json.contains("rating"));
    }

    @Test
    void shouldReturnRate() {
        System.out.println("Testing if it contains rate");
        assertTrue(json.contains("rate"));
    }

    @Test
    void shouldReturnCount() {
        System.out.println("Testing if it contains count");
        assertTrue(json.contains("count"));
    }
}