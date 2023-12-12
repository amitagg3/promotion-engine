package com.example.promotionEngine.service;

import com.example.promotionEngine.entities.Promotion;
import com.example.promotionEngine.repo.PromotionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PromotionServiceTest {

    @InjectMocks
    PromotionService promotionService;
    @Mock
    PromotionRepository promotionRepository;

    @DisplayName("Test for adding the promotions")
    @Test
    void addPromotionTest() {
        List<Promotion> promotionList = getAvailablePromotions();
        Assertions.assertNotNull(promotionRepository);
        Assertions.assertNotNull(promotionService);
        promotionService.addPromotion(promotionList);
        Mockito.verify(promotionRepository, Mockito.atLeast(3)).save(Mockito.any());
    }

    private static List<Promotion> getAvailablePromotions() {
        List<Promotion> promotionList = new ArrayList<>();
        Promotion promotion = Promotion.builder().promotionType("F").skuId1("A").quantity(3).discountedPrice(130).build();
        Promotion promotion1 = Promotion.builder().promotionType("F").skuId1("B").quantity(2).discountedPrice(45).build();
        Promotion promotion2 = Promotion.builder().promotionType("C").skuId1("C").skuId2("D").discountedPrice(30).build();
        promotionList.add(promotion);
        promotionList.add(promotion1);
        promotionList.add(promotion2);
        return promotionList;
    }
}