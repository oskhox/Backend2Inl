package com.store.backend2inl.repository;

import com.store.backend2inl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findUserByName (String username);
}
