package com.example.challenge4app.service;

import com.example.challenge4app.model.Product;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    Iterable<Product> getAllProducts();
}
