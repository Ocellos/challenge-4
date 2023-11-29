package com.example.challenge4app.service;

import com.example.challenge4app.model.Merchant;
import com.example.challenge4app.model.Product;

import java.util.List;

public interface MerchantService {
    void addMerchant(Merchant merchant);
    void updateMerchantStatus(Long merchantCode, boolean isOpen);
    List<Merchant> getAllMerchants();
    List<Merchant> getOpenMerchants();
    Merchant getMerchantByCode(Long merchantCode);
    void addProduct(Long merchantCode, Product product);
}
