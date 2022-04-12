package com.example.backend1assignment.Models;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Items {

    @Id
    @GeneratedValue
    private Long id;
    private String name, productNumber;

    public Items() {
    }

    public Items(String name, String productNumber) {
        this.name = name;
        this.productNumber = productNumber;
    }
}
