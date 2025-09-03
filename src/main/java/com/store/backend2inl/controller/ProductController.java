package com.store.backend2inl.controller;

import com.store.backend2inl.model.Product;
import com.store.backend2inl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RequestMapping("/products")
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
}