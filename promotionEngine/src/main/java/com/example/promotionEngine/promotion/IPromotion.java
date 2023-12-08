package com.example.promotionEngine.promotion;

import java.util.Map;

public interface IPromotion {
    int applyDiscount(Map<String, Integer> cart);
}
