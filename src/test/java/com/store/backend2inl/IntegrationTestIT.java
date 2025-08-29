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
        System.out.println("Testing if it contains all attributes");
        assertTrue(json.contains("id"));
        assertTrue(json.contains("title"));
        assertTrue(json.contains("price"));
        assertTrue(json.contains("description"));
        assertTrue(json.contains("category"));
        assertTrue(json.contains("image"));
        assertTrue(json.contains("rating"));
        assertTrue(json.contains("rate"));
        assertTrue(json.contains("count"));
    }
}