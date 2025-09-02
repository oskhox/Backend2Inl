package com.store.backend2inl.service;
import com.store.backend2inl.model.User;
import com.store.backend2inl.model.CustomerOrder;
import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.CustomerOrderRepository;
import com.store.backend2inl.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final ProductRepository productRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, ProductRepository productRepository) {
        this.customerOrderRepository = customerOrderRepository;
        this.productRepository = productRepository;
    }

    public void buyProduct(User user, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produkten hittades ej"));

        CustomerOrder order = new CustomerOrder();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(LocalDateTime.now());

        customerOrderRepository.save(order);
    }
}
