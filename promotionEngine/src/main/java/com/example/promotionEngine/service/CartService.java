package com.example.promotionEngine.service;

import com.example.promotionEngine.entities.Promotion;
import com.example.promotionEngine.model.Cart;
import com.example.promotionEngine.repo.PromotionRepository;
import com.example.promotionEngine.repo.SkuRepositroy;
import com.example.promotionEngine.entities.Sku;
import com.example.promotionEngine.promotion.IPromotion;
import com.example.promotionEngine.promotion.impl.ComboFixedPrice;
import com.example.promotionEngine.promotion.impl.NItemsFixedPrice;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class CartService {

    private Map<String, Integer> unitPrices;
    private IPromotion[] IPromotionList;

    @Autowired
    SkuRepositroy skuRepositroy;

    @Autowired
    PromotionRepository promotionRepository;

    public int calculateTotal(List<Cart> cartReq) {
        Map<String, Integer> cart = cartReq.stream().collect(Collectors.toMap(Cart::getSkuId, Cart::getQuantity));
        return calculateTotal(cart);
    }

    private int calculateTotal(Map<String, Integer> cart) {
        int total = 0;
        this.IPromotionList = getPromotions();

        this.unitPrices=getUnitPrices();

        for (IPromotion IPromotion : IPromotionList) {
            total += IPromotion.applyDiscount(cart);
        }

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemType = entry.getKey();
            int quantity = entry.getValue();

            int unitPrice = unitPrices.get(itemType);
            total += unitPrice * quantity;
        }

        return total;
    }

    private Map<String, Integer> getUnitPrices(){
        Map<String, Integer> unitPrices = new HashMap<>();
        List<Sku> listOfSku = skuRepositroy.findAll();
        for(Sku sku: listOfSku){
            unitPrices.put(sku.getSkuId(),sku.getPrice());
        }
        return unitPrices;
    }

    private IPromotion[] getPromotions(){
        List<Promotion> listOfPromotion=promotionRepository.findAll();
        IPromotion[] promotions=new IPromotion[listOfPromotion.size()];
        int indx = 0;
        for(Promotion pro: listOfPromotion){
            if ("F".equals(pro.getPromotionType())){
                promotions[indx++] = new NItemsFixedPrice(pro.getSkuId1(), pro.getQuantity(), pro.getDiscountedPrice());
            }else{
                promotions[indx++] = new ComboFixedPrice(pro.getSkuId1(), pro.getSkuId2(), pro.getDiscountedPrice());
            }
        }
        return promotions;
    }
}