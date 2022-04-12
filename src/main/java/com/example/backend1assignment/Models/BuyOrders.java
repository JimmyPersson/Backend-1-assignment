package com.example.backend1assignment.Models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class BuyOrders {

    @Id
    @GeneratedValue
    private Long id;
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Items> items;

    public BuyOrders() {

    }
    public BuyOrders(String orderNumber, List<Items> items, Customer customer) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.items = items;
    }
}
