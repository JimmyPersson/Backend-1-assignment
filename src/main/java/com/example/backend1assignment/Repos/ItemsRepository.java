package com.example.backend1assignment.Repos;

import com.example.backend1assignment.Models.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Items, Long> {
}
