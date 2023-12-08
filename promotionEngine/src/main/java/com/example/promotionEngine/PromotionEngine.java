package com.example.promotionEngine;

import com.example.promotionEngine.Promotion.Promotion;

import java.util.Map;

class PromotionEngine {

    private Map<String, Integer> unitPrices;
    private Promotion[] promotionList;

    public PromotionEngine(Map<String, Integer> unitPrices, Promotion[] promotions) {
        this.unitPrices = unitPrices;
        this.promotionList = promotions;
    }

    public int calculateTotal(Map<String, Integer> cart) {
        int total = 0;

        for (Promotion promotion : promotionList) {
            total += promotion.applyDiscount(cart);
        }

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemType = entry.getKey();
            int quantity = entry.getValue();

            int unitPrice = unitPrices.get(itemType);
            total += unitPrice * quantity;
        }

        return total;
    }
}