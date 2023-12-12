package com.example.promotionEngine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cart {

    String skuId;
    Integer quantity;
}
