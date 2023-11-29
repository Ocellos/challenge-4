package com.example.challenge4app.service.impl;

import com.example.challenge4app.model.Product;
import com.example.challenge4app.repository.ProductRepository;
import com.example.challenge4app.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
        LOGGER.info("Added a new product: {}", product.getName());
    }

    @Override
    public void updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
            LOGGER.info("Updated product with ID {}: {}", productId, existingProduct.getName());
        } else {
            LOGGER.error("Product with ID {} not found. Update failed.", productId);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        LOGGER.info("Deleted product with ID {}", productId);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        LOGGER.debug("Retrieved all products");
        return products;
    }
}
