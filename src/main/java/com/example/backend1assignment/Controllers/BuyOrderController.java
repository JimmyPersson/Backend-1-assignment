package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.BuyOrders;
import com.example.backend1assignment.Models.Customer;
import com.example.backend1assignment.Models.Items;
import com.example.backend1assignment.Repos.BuyOrdersRepository;
import com.example.backend1assignment.Repos.CustomerRepository;
import com.example.backend1assignment.Repos.ItemsRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class BuyOrderController {

    @Autowired
    private BuyOrdersRepository buyOrdersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @GetMapping("/all")
    public Iterable<BuyOrders> getAllOrders(){
        return buyOrdersRepository.findAll();
    }

    @GetMapping("/find/{customer_id}")
    public Iterable<BuyOrders> getOrderByCustomer(@PathVariable("customer_id") Long customerid){
        Customer customer = customerRepository.findById(customerid).get();
        return customer.getOrders();
    }

    /*@RequestMapping("/add")
    public ResponseEntity<?> makeNewOrder(@RequestParam Long customerId, @RequestParam String orderNumber, @RequestParam Long itemId){
        Customer customer = customerRepository.findById(customerId).get();
        Items item = itemsRepository.findById(itemId).get();
        List<Items> i = new ArrayList<>();
        i.add(item);

        BuyOrders buyOrders = new BuyOrders();
        buyOrders.setOrderNumber(orderNumber);
        buyOrders.setCustomer(customer);
        buyOrders.setItems(i);

        buyOrdersRepository.save(buyOrders);

        return new ResponseEntity<>(buyOrders, HttpStatus.CREATED);
    }*/


}
