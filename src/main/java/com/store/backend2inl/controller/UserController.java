package com.store.backend2inl.controller;

import com.store.backend2inl.model.User;
import com.store.backend2inl.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_" + user.getRole().toUpperCase());
    userRepo.save(user);
    return "redirect:/login";
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user" , new User());
        model.addAttribute("pageTitle", "Registrera användare");
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Logga in");
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("pageTitle", "Admin");
        model.addAttribute("linkHome", "Startsida");
        model.addAttribute("linkProducts", "Produkter");
        model.addAttribute("linkOrders", "Ordrar");
        return "admin";
    }

    @GetMapping("/customer")
    public String customer(Model model) {
        model.addAttribute("pageTitle", "Kundprofil");
        model.addAttribute("linkProducts", "Produkter");
        return "customer";
    }

}
