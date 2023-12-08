package com.example.promotionEngine.repo;

import com.example.promotionEngine.entities.Promotion;
import com.example.promotionEngine.entities.PromotionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
