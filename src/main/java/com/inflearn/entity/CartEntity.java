package com.inflearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    private int order_id;
    private String customer_id;
    private int product_id;
    private int order_qty;
    private String order_date;

    private String product_name;
    private int product_inventory;
    private int product_price;

    private int amount;
}