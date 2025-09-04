package com.store.backend2inl.controller;
import com.store.backend2inl.model.CustomerOrder;
import com.store.backend2inl.model.User;
import com.store.backend2inl.service.CustomerOrderService;
import com.store.backend2inl.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class CustomerOrderController {

    private final CustomerOrderService CustomerOrderService;
    private final UserService userService;
    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService CustomerOrderService, UserService userService, CustomerOrderService customerOrderService) {
        this.CustomerOrderService = CustomerOrderService;
        this.userService = userService;
        this.customerOrderService = customerOrderService;
    }

    @PostMapping("/{productId}")
    public String buyProduct(@PathVariable Long productId, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.findUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Användare ej hittad"));
        CustomerOrderService.buyProduct(user, productId);

        return "/orderConfirmation";
    }

    @PostMapping("/orderConfirmation")
    public String completeOrder(Model model) {
        model.addAttribute("message", "Köp genomfört!");
        return "orderConfirmation";
    }

        @GetMapping("/orders")
        public String orders(Model model) {
            try {
                List<CustomerOrder> allOrders = CustomerOrderService.getAllOrders();
                model.addAttribute("orders", allOrders);
                model.addAttribute("pageTitle", "Alla ordrar");
                return "orders";
            } catch (Exception e) {
                System.err.println("Fel vid hämtning av ordrar: " + e.getMessage());
                model.addAttribute("errorMessage", "Kunde inte hämta ordrar just nu.");
            }
            return "orders";
        }

        @GetMapping("/deleteOrder")
        public String deleteOrder(@RequestParam("orderId") long orderId) {
            customerOrderService.deleteOrder(orderId);
            return "redirect:/products/orders";
        }
    }