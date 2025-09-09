package com.store.backend2inl.security;

import com.store.backend2inl.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepo userRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepo.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().replace("ROLE_", ""))
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Användaren hittades ej"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/products").permitAll()
                        .requestMatchers("/admin","/orders").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                                if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                    response.sendRedirect("/admin");
                                } else {
                                    response.sendRedirect("/customer");
                                }
                        })
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .headers(headers -> headers.disable());

        return http.build();
    }
}