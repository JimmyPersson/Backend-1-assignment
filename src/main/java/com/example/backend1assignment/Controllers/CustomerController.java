package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.Customer;
import com.example.backend1assignment.Models.DTO.CustomerDTO;
import com.example.backend1assignment.Repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable("id") Long id){
        return customerRepository.findById(id).get();
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());

        customerRepository.save(customer);

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

}
