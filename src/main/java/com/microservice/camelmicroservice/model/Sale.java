package com.microservice.camelmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sale {
    private int id_sale;
    private int id_employee;
    private int id_branch_office;
    private String name_branch_office;
    private double total_sale;
    private String description;
    private String date_sale;
    private String name_employee;
}
