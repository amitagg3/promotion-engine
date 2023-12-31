package com.example.promotionEngine.controller;

import com.example.promotionEngine.entities.Promotion;
import com.example.promotionEngine.entities.Sku;
import com.example.promotionEngine.model.Cart;
import com.example.promotionEngine.service.CartService;
import com.example.promotionEngine.service.PromotionService;
import com.example.promotionEngine.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class PromotionEngineController {

    @Autowired
    SkuService skuService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    CartService cartService;

    @PostMapping("/updateSku")
    public void updateSKUIDsAndPrice(@RequestBody List<Sku> sku) {
        skuService.updateSKUIDsAndPrice(sku);
    }

    @PostMapping("/updatePromotions")
    public void addPromotions(@RequestBody List<Promotion> promotions) {
        promotionService.addPromotion(promotions);
    }

    @PostMapping("/calculateTotal")
    public Integer calculateTotal(@RequestBody List<Cart> cart) {
        return cartService.calculateTotal(cart);
    }

}
