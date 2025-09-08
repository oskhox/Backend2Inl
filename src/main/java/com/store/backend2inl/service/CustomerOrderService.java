package com.store.backend2inl.service;
import com.store.backend2inl.model.User;
import com.store.backend2inl.model.CustomerOrder;
import com.store.backend2inl.model.Product;
import com.store.backend2inl.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final ProductService productService;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, ProductService productService) {
        this.customerOrderRepository = customerOrderRepository;
        this.productService = productService;
    }

    @Transactional
    public void buyProduct(User user, Long productId) {
        Product product = productService.getProductById(productId);
        CustomerOrder order = new CustomerOrder();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(LocalDateTime.now());

        customerOrderRepository.save(order);
    }

    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }

    public void deleteOrder(long orderId) {
        customerOrderRepository.deleteById(orderId);
    }
}