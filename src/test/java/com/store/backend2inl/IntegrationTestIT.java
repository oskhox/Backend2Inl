package com.store.backend2inl;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTestIT {

    @Test
    void shouldReturnJSONFromApi() {
        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        System.out.println("Testing JSON");
        assertTrue(json.contains("title"));
    }
}