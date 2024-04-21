package com.inflearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private int order_id;
    private String customer_id;
    private int product_id;
    private int order_qty;
    private String order_date;
}