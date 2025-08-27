package com.store.backend2inl.controller;

import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import com.store.backend2inl.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO: Gör om till Controller för Thymeleaf

//@Controller
@RestController
public class ProductController {

    @Autowired
    ApiService apiService;

    public ProductController(ApiService apiService) {
        this.apiService = apiService;

    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        try {
            apiService.fetchAndSaveProducts();
            List<Product> products = apiService.getAllProducts();

            model.addAttribute("products", products);
            model.addAttribute("pageTitle", "Produkter");
            model.addAttribute("productPrice", "Pris");
            model.addAttribute("productDescription", "Beskrivning");

            return "products";

        } catch (Exception e) {
            System.err.println("Fel vid hämtning av produkter: " + e.getMessage());
            model.addAttribute("errorMessage", "Kunde inte hämta produkter just nu.");
        }
        return "products";
    }



/*
    //RESTAPI

    @GetMapping("/fetch/all")
    public ResponseEntity<List<Product>> fetchProducts() throws Exception {
        apiService.fetchAndSaveProducts();
        return ResponseEntity.ok().body(ap.findAll());
    }

    */

}