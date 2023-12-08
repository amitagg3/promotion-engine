package com.example.promotionEngine.promotion.impl;

import com.example.promotionEngine.promotion.IPromotion;

import java.util.Map;

public class NItemsFixedPrice implements IPromotion {

    private String itemType;
    private int quantity;
    private int price;

    public NItemsFixedPrice(String itemType, int quantity, int price) {
        this.itemType = itemType;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public int applyDiscount(Map<String, Integer> cart) {
        if (cart.containsKey(itemType) && cart.get(itemType) >= quantity) {
            int discountedItems = cart.get(itemType) / quantity;
            int remainingItems = cart.get(itemType) % quantity;
            cart.put(itemType, remainingItems);
            return price * discountedItems;
        }
        return 0;
    }
}
