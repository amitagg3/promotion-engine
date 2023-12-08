package com.example.promotionEngine.service;

import com.example.promotionEngine.entities.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.promotionEngine.repo.PromotionRepository;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    public void addPromotion(List<Promotion> promotions) {
        for (Promotion promotion : promotions)
            promotionRepository.save(promotion);
    }
}
