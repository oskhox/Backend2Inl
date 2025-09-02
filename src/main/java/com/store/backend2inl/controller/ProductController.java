package com.store.backend2inl.controller;

import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.ProductRepository;
import com.store.backend2inl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        try {
            List<Product> products = productService.getAllProducts();

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
    public ResponseEntity<List<Product>> fetchProducts() {

        try {
            apiService.fetchAndSaveProducts();
        } catch (Exception e) {
            System.out.println("There was a problem fetching the products");
        }
        return ResponseEntity.ok().body(productRepository.findAll());
    }

    */

}