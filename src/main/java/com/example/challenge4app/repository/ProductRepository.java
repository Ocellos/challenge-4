package com.example.challenge4app.repository;

import com.example.challenge4app.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
