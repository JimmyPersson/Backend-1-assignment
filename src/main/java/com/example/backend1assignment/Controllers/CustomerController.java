package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.Customer;
import com.example.backend1assignment.Repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/add")
    public String addCustomer(@RequestParam String name, @RequestParam String address,
                              @RequestParam String email, @RequestParam (required = false) String password){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPassword(password);

        customerRepository.save(customer);

        return "Customer with name " + name + " has been saved!";
    }
}
