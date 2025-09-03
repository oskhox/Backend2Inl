package com.store.backend2inl.repository;

import com.store.backend2inl.model.Product;
import com.store.backend2inl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
