package com.example.promotionEngine.Promotion;

import java.util.Map;

public interface Promotion {
    int applyDiscount(Map<String, Integer> cart);
}
