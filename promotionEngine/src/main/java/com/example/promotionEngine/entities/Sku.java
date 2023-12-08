package com.example.promotionEngine.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Sku {
    @Id
    String skuId;
    Integer price;
}
