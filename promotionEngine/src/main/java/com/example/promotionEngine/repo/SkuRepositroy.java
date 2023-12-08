package com.example.promotionEngine.repo;

import com.example.promotionEngine.entities.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepositroy extends JpaRepository<Sku, String> {
}
