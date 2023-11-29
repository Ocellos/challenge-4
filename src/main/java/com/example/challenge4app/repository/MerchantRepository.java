package com.example.challenge4app.repository;

import com.example.challenge4app.model.Merchant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    List<Merchant> findAllByOpen(boolean open);
}
