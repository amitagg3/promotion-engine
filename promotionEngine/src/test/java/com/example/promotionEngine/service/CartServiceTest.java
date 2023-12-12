package com.example.promotionEngine.service;

import com.example.promotionEngine.entities.Promotion;
import com.example.promotionEngine.entities.Sku;
import com.example.promotionEngine.model.Cart;
import com.example.promotionEngine.repo.PromotionRepository;
import com.example.promotionEngine.repo.SkuRepositroy;
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

import java.util.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CartServiceTest {
    @InjectMocks
    CartService cartService;

    @Mock
    PromotionRepository promotionRepository;
    @Mock
    SkuRepositroy skuRepositroy;

    @DisplayName("Test for calculating total amount for different scenarios of cart")
    @Test
    void calculateTotalTest() {
        Map<String, List<Cart>> cartInput = getDifferentScenariosOfCart();
        Map<String, Integer> expectedOutput = getExpectedAmountForEachScenario();
        List<Promotion> promotionList = getAvailablePromotions();
        List<Sku> skuList = getAvailableSkus();
        Mockito.when(promotionRepository.findAll()).thenReturn(promotionList);
        Mockito.when(skuRepositroy.findAll()).thenReturn(skuList);
        Assertions.assertNotNull(cartService);
        for (Map.Entry<String, List<Cart>> entry : cartInput.entrySet()) {
            System.out.println(entry.getKey() + " expected value=" + expectedOutput.get(entry.getKey()));
            Assertions.assertEquals(expectedOutput.get(entry.getKey()),
                    cartService.calculateTotal(entry.getValue()));
        }
    }

    private static List<Sku> getAvailableSkus() {
        List<Sku> skuList = new ArrayList<>();
        Sku sku1 = Sku.builder().skuId("A").price(50).build();
        Sku sku2 = Sku.builder().skuId("B").price(30).build();
        Sku sku3 = Sku.builder().skuId("C").price(20).build();
        Sku sku4 = Sku.builder().skuId("D").price(15).build();
        skuList.add(sku1);
        skuList.add(sku2);
        skuList.add(sku3);
        skuList.add(sku4);
        return skuList;
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

    private static Map<String, Integer> getExpectedAmountForEachScenario() {
        Map<String, Integer> expectedOutput = new HashMap<>();
        expectedOutput.put("Scenario-A", 100);
        expectedOutput.put("Scenario-B", 370);
        expectedOutput.put("Scenario-C", 280);
        return expectedOutput;
    }

    private static Map<String, List<Cart>> getDifferentScenariosOfCart() {
        Map<String, List<Cart>> cartInput = new HashMap<>();
        cartInput.put("Scenario-A", new ArrayList<>(Arrays.asList(Cart.builder().skuId("A").quantity(1).build(),
                Cart.builder().skuId("B").quantity(1).build(),
                Cart.builder().skuId("C").quantity(1).build())));
        cartInput.put("Scenario-B", new ArrayList<>(Arrays.asList(Cart.builder().skuId("A").quantity(5).build(),
                Cart.builder().skuId("B").quantity(5).build(),
                Cart.builder().skuId("C").quantity(1).build())));
        cartInput.put("Scenario-C", new ArrayList<>(Arrays.asList(Cart.builder().skuId("A").quantity(3).build(),
                Cart.builder().skuId("B").quantity(5).build(),
                Cart.builder().skuId("C").quantity(1).build(),
                Cart.builder().skuId("D").quantity(1).build())));
        return cartInput;
    }
}