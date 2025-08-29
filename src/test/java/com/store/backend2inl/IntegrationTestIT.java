package com.store.backend2inl;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTestIT {

    String url = "https://fakestoreapi.com/products";
    RestTemplate restTemplate = new RestTemplate();
    String json = restTemplate.getForObject(url, String.class);

    @Test
    void shouldReturnJSONFromApi() {
        System.out.println("Testing if it contains title");
        assertTrue(json.contains("title"));
    }

    @Test
    void shouldReturnJSONFromApi2() {
        System.out.println("Testing if it contains price");
        assertTrue(json.contains("price"));
    }

    @Test
    void shouldReturnJSONFromApi3() {
        System.out.println("Testing if specific product is present");
        assertTrue(json.contains("Fjallraven"));
    }
}