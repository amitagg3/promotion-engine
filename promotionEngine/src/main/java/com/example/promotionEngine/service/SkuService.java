package com.example.promotionEngine.service;

import com.example.promotionEngine.repo.SkuRepositroy;
import com.example.promotionEngine.entities.Sku;
import com.example.promotionEngine.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SkuService {

    @Autowired
    SkuRepositroy skuRepositroy;

    @Autowired
    PromotionEngineService engine;


    public void updateSKUIDsAndPrice(List<Sku> sku){
        for(Sku skuId: sku)
            skuRepositroy.save(skuId);
    }

    public int calculateTotal(List<Cart> cartReq) {
        Map<String, Integer> cart = cartReq.stream().collect(Collectors.toMap(Cart::getSkuId, Cart::getQuantity));
        return engine.calculateTotal(cart);
    }

}
