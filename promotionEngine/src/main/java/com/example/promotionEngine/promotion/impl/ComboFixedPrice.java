package com.example.promotionEngine.promotion.impl;

import com.example.promotionEngine.promotion.IPromotion;

import java.util.Map;

public class ComboFixedPrice implements IPromotion {

    private String itemType1;
    private String itemType2;
    private int price;

    public ComboFixedPrice(String itemType1, String itemType2, int price) {
        this.itemType1 = itemType1;
        this.itemType2 = itemType2;
        this.price = price;
    }

    @Override
    public int applyDiscount(Map<String, Integer> cart) {
        if (cart.containsKey(itemType1) && cart.containsKey(itemType2)) {
            int commonQuantity = Math.min(cart.get(itemType1), cart.get(itemType2));
            cart.put(itemType1, cart.get(itemType1) - commonQuantity);
            cart.put(itemType2, cart.get(itemType2) - commonQuantity);
            return price * commonQuantity;
        }
        return 0;
    }
}