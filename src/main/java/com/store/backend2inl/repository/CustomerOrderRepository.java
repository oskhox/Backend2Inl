package com.store.backend2inl.repository;

import com.store.backend2inl.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {

}
