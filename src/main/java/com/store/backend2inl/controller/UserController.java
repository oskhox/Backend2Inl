package com.store.backend2inl.controller;


import com.store.backend2inl.model.User;
import com.store.backend2inl.repository.UserRepo;
import com.store.backend2inl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user" , new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user" , new User());
        return "register";
    }

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
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model){
        return userService.findUserByUsername(username).
                filter(user -> new BCryptPasswordEncoder().matches(password, user.getPassword())).
                map(user -> {
                    model.addAttribute("username" , user.getUsername());
                    return "products";
                }).orElseGet(() -> {
                    User failedUser = new User();
                    failedUser.setUsername(username);
                    model.addAttribute("user" , failedUser);
                    model.addAttribute("error" , "Incorrect username or password!");
                    return "login";
                });

    }
}
