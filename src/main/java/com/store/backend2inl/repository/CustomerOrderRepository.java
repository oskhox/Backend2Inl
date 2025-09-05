package com.store.backend2inl.repository;

import com.store.backend2inl.model.CustomerOrder;
import com.store.backend2inl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {

}
