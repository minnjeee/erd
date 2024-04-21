package com.inflearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    private String customer_id;
    private String customer_name;
    private String customer_pwd;
    private int customer_age;
    private String customer_job;
    private String customer_rating;
    private int customer_reserves;
}