package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Repos.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;
}
