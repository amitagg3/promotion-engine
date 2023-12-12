package com.example.promotionEngine.entities;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@Builder
public class Sku {
    @Id
    String skuId;
    Integer price;
}
