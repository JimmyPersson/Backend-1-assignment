package com.example.backend1assignment.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name, address, email, password;

    @OneToMany(mappedBy = "customer")
    private List<BuyOrders> orders;

    public Customer() {
    }

    public Customer(String name, String address, String email, String password, List<BuyOrders> orders) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.orders = orders;
    }
}