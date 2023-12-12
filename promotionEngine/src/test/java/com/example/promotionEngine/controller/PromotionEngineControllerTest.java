package com.example.promotionEngine.controller;

import com.example.promotionEngine.entities.Promotion;
import com.example.promotionEngine.entities.Sku;
import com.example.promotionEngine.model.Cart;
import com.example.promotionEngine.service.CartService;
import com.example.promotionEngine.service.PromotionService;
import com.example.promotionEngine.service.SkuService;
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
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PromotionEngineControllerTest {

    @InjectMocks
    PromotionEngineController promotionEngineController;

    @Mock
    PromotionService promotionService;

    @Mock
    SkuService skuService;

    @Mock
    CartService cartService;


    @DisplayName("Controller test for adding the SKU's")
    @Test
    void updateSKUIDsAndPriceTest() {
        List<Sku> skuList = new ArrayList<>();
        Sku sku1 = Sku.builder().skuId("A").price(50).build();
        Sku sku2 = Sku.builder().skuId("B").price(30).build();
        Sku sku3 = Sku.builder().skuId("C").price(20).build();
        Sku sku4 = Sku.builder().skuId("D").price(15).build();
        skuList.add(sku1);
        skuList.add(sku2);
        skuList.add(sku3);
        skuList.add(sku4);
        promotionEngineController.updateSKUIDsAndPrice(skuList);
        Mockito.verify(skuService, Mockito.atLeast(1)).updateSKUIDsAndPrice(skuList);
        Assertions.assertTrue(true);
    }

    @DisplayName("Controller test for adding the promotions")
    @Test
    void addPromotionsTest() {
        List<Promotion> promotionList = new ArrayList<>();
        Promotion promotion = Promotion.builder().promotionType("F").skuId1("A").quantity(3).discountedPrice(130).build();
        Promotion promotion1 = Promotion.builder().promotionType("F").skuId1("B").quantity(2).discountedPrice(45).build();
        Promotion promotion2 = Promotion.builder().promotionType("C").skuId1("C").skuId2("D").discountedPrice(30).build();
        promotionList.add(promotion);
        promotionList.add(promotion1);
        promotionList.add(promotion2);
        promotionEngineController.addPromotions(promotionList);
        Mockito.verify(promotionService, Mockito.atLeast(1)).addPromotion(promotionList);
    }

    @DisplayName("Controller test for calculating amount after applying promotions")
    @Test
    void calculateTotalTest() {
        List<Cart> cartList = new ArrayList<>(Arrays.asList(Cart.builder().skuId("A").quantity(1).build(),
                Cart.builder().skuId("B").quantity(1).build(),
                Cart.builder().skuId("C").quantity(1).build()));
        Assertions.assertNotNull(promotionEngineController);
        promotionEngineController.calculateTotal(cartList);
        Mockito.verify(cartService, Mockito.atLeast(1)).calculateTotal(cartList);
    }

}