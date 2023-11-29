package com.example.challenge4app.repository;

import com.example.challenge4app.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}