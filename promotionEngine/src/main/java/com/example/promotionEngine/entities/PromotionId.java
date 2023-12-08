package com.example.promotionEngine.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PromotionId implements Serializable {
    String skuId1;
    String skuId2;
}
