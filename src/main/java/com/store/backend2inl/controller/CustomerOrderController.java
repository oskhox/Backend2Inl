package com.store.backend2inl.controller;
import com.store.backend2inl.model.User;
import com.store.backend2inl.service.CustomerOrderService;
import com.store.backend2inl.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
@RequestMapping("/products")
public class CustomerOrderController {

    private final CustomerOrderService orderService;
    private final UserService userService;

    public CustomerOrderController(CustomerOrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @PostMapping("/{productId}")
    public String buyProduct(@PathVariable Long productId, Principal principal) {

        User user = userService.findUserByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Användare ej hittad"));
        orderService.buyProduct(user, productId);

        return "/orderConfirmation";
    }

    @PostMapping("/orderConfirmation")
    public String completeOrder(Model model) {
        model.addAttribute("message", "Köp genomfört!");
        return "orderConfirmation";
    }


}
