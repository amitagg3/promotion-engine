package com.example.promotionEngine.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    String promotionType;
    String skuId1;
    String skuId2;
    Integer quantity;
    Integer discountedPrice;

}
