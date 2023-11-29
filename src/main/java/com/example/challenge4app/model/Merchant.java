package com.example.challenge4app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "merchants")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_code") // Change the column name for id
    private Long merchantCode; // Change the field name

    @lombok.Setter
    @lombok.Getter
    private String name;
    private String location;
    private boolean open;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.EAGER)
    private List<Product> products;


    // constructors, getters, and setters

    public Merchant() {
        // default constructor
    }

    public Merchant(String name, String location, boolean open) {
        this.name = name;
        this.location = location;
        this.open = open;
    }

    public Long getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(Long merchantCode) {
        this.merchantCode = merchantCode;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    @Override
    public String toString() {
        return "Merchant{" +
                "merchantCode=" + merchantCode +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", open=" + open +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
