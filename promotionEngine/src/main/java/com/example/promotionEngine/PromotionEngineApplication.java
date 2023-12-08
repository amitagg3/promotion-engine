package com.example.promotionEngine;

import com.example.promotionEngine.Promotion.Promotion;
import com.example.promotionEngine.Promotion.impl.ComboFixedPrice;
import com.example.promotionEngine.Promotion.impl.NItemsFixedPrice;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PromotionEngineApplication {
    public static void main(String[] args) {
//            SpringApplication.run(PromotionEngineApplication.class, args);
        /*
        todo:
        Entites
        1. Item/Sku :- id, price entity
        2. Promotion:- Type(F/C), Item1/Sku, Item2/Sku, quantity, discounted price


        * */
        Map<String, Integer> unitPrices = new HashMap<>();
        unitPrices.put("A", 50);
        unitPrices.put("B", 30);
        unitPrices.put("C", 20);
        unitPrices.put("D", 15);

        Promotion[] promotions = {/*
            todo : List of Promotions which we will get from the Db
        */
                new NItemsFixedPrice("A", 3, 130),
                new NItemsFixedPrice("B", 2, 45),
                new ComboFixedPrice("C", "D", 30)
        };

        Map<String, Integer> cart = new HashMap<>();
        cart.put("A", 5);
        cart.put("B", 5);
        cart.put("C", 1);
//        cart.put("D", 1);

        PromotionEngine engine = new PromotionEngine(unitPrices, promotions);
        int total = engine.calculateTotal(cart);

        System.out.println("Total: " + total);
    }
}
