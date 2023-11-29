package com.example.challenge4app.service.impl;

import com.example.challenge4app.model.Order;
import com.example.challenge4app.repository.OrderRepository;
import com.example.challenge4app.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
        LOGGER.info("Created a new order with ID {}", order.getId());
    }

    @Override
    public Iterable<Order> getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        LOGGER.debug("Retrieved all orders");
        return orders;
    }
}
