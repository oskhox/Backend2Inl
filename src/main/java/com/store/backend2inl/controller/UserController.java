package com.store.backend2inl.controller;

import com.store.backend2inl.model.User;
import com.store.backend2inl.repository.UserRepo;
import com.store.backend2inl.service.ProductService;
import com.store.backend2inl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "register";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }



    /*
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user" , new User());
        return "login";
    }
*/
/*
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model){
        if(userRepo.findByUsername(username).isPresent()){
            model.addAttribute("error" , "That username is already taken!");
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);

        model.addAttribute("success" , "User registered successfully!");
        model.addAttribute("user" , user);
        model.addAttribute("password" , password);
        return "login";
    }
*/

    /*
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model){
        return userService.findUserByUsername(username).
                filter(user -> new BCryptPasswordEncoder().matches(password, user.getPassword())).
                map(user -> {
                    model.addAttribute("pageTitle" , "Alla produkter");
                    model.addAttribute("products", productService.getAllProducts());
                    return "products";
                }).orElseGet(() -> {
                    model.addAttribute("user" , new User());
                    model.addAttribute("errorMessage" , "Incorrect username or password!");
                    return "login";
                });

    }


     */
}
