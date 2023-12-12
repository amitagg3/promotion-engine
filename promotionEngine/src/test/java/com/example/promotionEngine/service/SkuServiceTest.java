package com.example.promotionEngine.service;

import com.example.promotionEngine.entities.Sku;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SkuServiceTest {

    @InjectMocks
    SkuService skuService;
    @Mock
    SkuRepositroy skuRepositroy;

    @DisplayName("Test for adding the SKU's")
    @Test
    void updateSKUIDsAndPriceTest() {
        List<Sku> skuList = getAvailableSkus();
        Assertions.assertNotNull(skuRepositroy);
        skuService.updateSKUIDsAndPrice(skuList);
        Mockito.verify(skuRepositroy, Mockito.atLeast(4)).save(Mockito.any());
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
}