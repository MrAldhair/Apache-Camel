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
    
    @Override
    public String toString(){
        return 
                "{\"id_sale\": "+this.id_sale+", "+
                "\"id_employee\": "+this.id_employee+", "+
                "\"id_branch_office\": "+this.id_branch_office+", "+
                "\"name_branch_office\": \""+this.name_branch_office+"\", "+
                "\"total_sale\": "+this.total_sale+", "+
                "\"description\": \""+this.description+"\", "+
                "\"date_sale\": \""+this.date_sale+"\", "+
                "\"name_employee\": \""+this.name_employee+"\"}";
    }
    
}
