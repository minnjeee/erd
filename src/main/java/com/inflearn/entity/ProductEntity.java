package com.inflearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    private int product_id;
    private String product_name;
    private int product_inventory;
    private int product_price;
    private String product_manufacturer;
}