package com.microservice.camelmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sale;
    private int id_employee;
    private int id_branch_office;
    private double total_sale;
    private String description;
    private String date_sale;
    private String name_employee;
    //Datos de la sucursal
    private String name_branch_office;
    private String state_branch_office;
    private String city_branch_office;
    private String street_branch_office;
    private Integer number_branch_office;
    private Integer zip_code_branch_office;
    //folio generado en POS
    private String folio;

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
                "\"name_employee\": \""+this.name_employee+"\", "+
                "\"state_branch_office\": \""+this.state_branch_office+"\", "+
                "\"city_branch_office\": \""+this.city_branch_office+"\", "+
                "\"street_branch_office\": \""+this.street_branch_office+"\", "+
                "\"number_branch_office\": "+this.number_branch_office+", "+
                "\"zip_code_branch_office\": "+this.zip_code_branch_office+", "+
                    "\"folio\": \""+this.folio+"\" "+"}";
    }
}
