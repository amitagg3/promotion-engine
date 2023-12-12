package com.example.promotionEngine.service;

import com.example.promotionEngine.entities.Sku;
import com.example.promotionEngine.repo.SkuRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuService {

    @Autowired
    SkuRepositroy skuRepositroy;

    public void updateSKUIDsAndPrice(List<Sku> sku) {
        for (Sku skuId : sku)
            skuRepositroy.save(skuId);
    }

}
