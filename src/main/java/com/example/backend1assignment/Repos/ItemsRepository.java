package com.example.backend1assignment.Repos;

import com.example.backend1assignment.Models.Items;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemsRepository extends CrudRepository<Items, Long> {

    Items findByNameAndProductNumber(String name, String productNumber);
}
