package com.store.backend2inl.service;


import com.store.backend2inl.model.User;
import com.store.backend2inl.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Optional<User> findUserByUsername(String username){

        return userRepo.findByUsername(username);
    }
}
