package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.BuyOrders;
import com.example.backend1assignment.Models.Customer;
import com.example.backend1assignment.Models.DTO.BuyOrderDTO;
import com.example.backend1assignment.Models.Items;
import com.example.backend1assignment.Repos.BuyOrdersRepository;
import com.example.backend1assignment.Repos.CustomerRepository;
import com.example.backend1assignment.Repos.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BuyOrdersRepository buyOrdersRepository;

    @GetMapping("/all")
    public Iterable<Items> getAllItems(){
        return itemsRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Items getItemById(@PathVariable("id") Long id){
        return itemsRepository.findById(id).get();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody Items item){
        item.setName(item.getName());
        item.setProductNumber(item.getProductNumber());
        itemsRepository.save(item);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    /*@RequestMapping("/buy")
    public ResponseEntity<?> newOrder(@RequestParam Long customerId, @RequestParam String itemName, @RequestParam String productNumber){
        Customer customer = customerRepository.findById(customerId).get();
        Items item = itemsRepository.findByNameAndProductNumber(itemName, productNumber);

        List<Items> myOrder = new ArrayList<>();
        myOrder.add(item);

        BuyOrders buyOrders = new BuyOrders();
        //buyOrders.setOrderNumber(orderNumber);
        buyOrders.setCustomer(customer);
        buyOrders.setItems(myOrder);

        buyOrdersRepository.save(buyOrders);

        return new ResponseEntity<>(buyOrders, HttpStatus.CREATED);
    }*/

    @RequestMapping("/buy")
    public ResponseEntity<?> newOrder(@RequestBody BuyOrderDTO buyOrderDTO){
        Customer customer = customerRepository.findById(buyOrderDTO.getCustomerId()).get();
        Items item = itemsRepository.findById(buyOrderDTO.getItemId()).get();

        List<Items> myOrder = new ArrayList<>();
        myOrder.add(item);

        BuyOrders buyOrders = new BuyOrders();
        //buyOrders.setOrderNumber(orderNumber);
        buyOrders.setCustomer(customer);
        buyOrders.setItems(myOrder);

        buyOrdersRepository.save(buyOrders);

        return new ResponseEntity<>(buyOrders, HttpStatus.CREATED);
    }



}
