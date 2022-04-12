package com.example.backend1assignment.Repos;

import com.example.backend1assignment.Models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
