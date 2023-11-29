package com.example.challenge4app.service;

import com.example.challenge4app.model.Order;

public interface OrderService {
    void createOrder(Order order);
    Iterable<Order> getAllOrders();
}

