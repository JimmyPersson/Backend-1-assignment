package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.BuyOrders;
import com.example.backend1assignment.Models.Customer;
import com.example.backend1assignment.Repos.BuyOrdersRepository;
import com.example.backend1assignment.Repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class BuyOrderController {

    @Autowired
    private BuyOrdersRepository buyOrdersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public Iterable<BuyOrders> getAllOrders(){
        return buyOrdersRepository.findAll();
    }

    @GetMapping("/{customer_id}")
    public Iterable<BuyOrders> getOrderByCustomer(@PathVariable("customer_id") Long customerid){
        Customer customer = customerRepository.findById(customerid).get();
        return customer.getOrders();
    }
}
