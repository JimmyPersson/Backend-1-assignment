package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.Items;
import com.example.backend1assignment.Repos.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;

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

}
