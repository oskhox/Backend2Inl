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
import java.util.List;

@Controller
@RequestMapping("/order")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;
    private final UserService userService;

    public CustomerOrderController(CustomerOrderService customerOrderService, UserService userService) {
        this.customerOrderService = customerOrderService;
        this.userService = userService;
    }

    @PostMapping("/{productId}")
    public String buyProduct(@PathVariable Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Användare ej hittad"));

        customerOrderService.buyProduct(user, productId);

        return "redirect:/order/orderConfirmation";
    }

    @GetMapping("/orderConfirmation")
    public String completeOrder(Model model) {
        model.addAttribute("pageTitle", "Orderbekräftelse");
        model.addAttribute("message", "Tack för din order!");
        return "orderConfirmation";
    }

    @GetMapping("/allOrders")
    public String orders(Model model) {
        try {
            List<CustomerOrder> allOrders = customerOrderService.getAllOrders();
            model.addAttribute("orders", allOrders);
            model.addAttribute("pageTitle", "Alla ordrar");
            model.addAttribute("linkAdmin", "Admin");
            model.addAttribute("linkProducts", "Produkter");
            return "allOrders";
        } catch (Exception e) {
            System.err.println("Fel vid hämtning av ordrar: " + e.getMessage());
            model.addAttribute("errorMessage", "Kunde inte hämta ordrar just nu.");
        }
        return "allOrders";
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("orderId") long orderId) {
        customerOrderService.deleteOrder(orderId);
        return "redirect:/order/allOrders";
    }
}