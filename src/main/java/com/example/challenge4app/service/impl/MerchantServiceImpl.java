package com.example.challenge4app.service.impl;

import com.example.challenge4app.model.Merchant;
import com.example.challenge4app.model.Product;
import com.example.challenge4app.repository.MerchantRepository;
import com.example.challenge4app.repository.ProductRepository;
import com.example.challenge4app.service.MerchantService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
        LOGGER.info("Added a new merchant: {}", merchant.getName());
    }

    @Override
    public void updateMerchantStatus(Long merchantCode, boolean isOpen) {
        Merchant existingMerchant = merchantRepository.findById(merchantCode).orElse(null);
        if (existingMerchant != null) {
            existingMerchant.setOpen(isOpen);
            merchantRepository.save(existingMerchant);
            LOGGER.info("Updated merchant status with ID {}: {}", merchantCode, isOpen ? "Open" : "Closed");
        } else {
            LOGGER.error("Merchant with ID {} not found. Update failed.", merchantCode);
        }
    }

    @Override
    public List<Merchant> getAllMerchants() {
        Iterable<Merchant> iterableMerchants = merchantRepository.findAll();
        List<Merchant> merchants = new ArrayList<>();
        iterableMerchants.forEach(merchants::add);
        return merchants;
    }

    @Override
    public List<Merchant> getOpenMerchants() {
        return merchantRepository.findAllByOpen(true);
    }

    @Override
    public Merchant getMerchantByCode(Long merchantCode) {
        Optional<Merchant> optionalMerchant = merchantRepository.findById(merchantCode);
        return optionalMerchant.orElse(null);
    }

    @Override
    @Transactional
    public void addProduct(Long merchantCode, Product product) {
        Optional<Merchant> optionalMerchant = merchantRepository.findById(merchantCode);

        if (optionalMerchant.isPresent()) {
            Merchant merchant = optionalMerchant.get();
            List<Product> products = merchant.getProducts();

            if (products == null) {
                products = new ArrayList<>();
            }

            product.setMerchant(merchant);
            products.add(product);
            merchant.setProducts(products);

            // Save the merchant
            merchantRepository.save(merchant);

            // Save the product using its own repository
            productRepository.save(product);

            LOGGER.info("Added a new product '{}' to merchant '{}'", product.getName(), merchant.getName());
        } else {
            LOGGER.error("Merchant with ID {} not found. Product addition failed.", merchantCode);
        }
    }

}
