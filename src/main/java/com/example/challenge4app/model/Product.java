package com.example.challenge4app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code") // Change the column name for id
    private Long productCode; // Change the field name

    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "merchant_code") // Define the foreign key column
    private Merchant merchant;

    // constructors, getters, and setters

    public Product() {
        // default constructor
    }

    public Product(String name, double price, Merchant merchant) {
        this.name = name;
        this.price = price;
        this.merchant = merchant;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    // Update other methods accordingly

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
