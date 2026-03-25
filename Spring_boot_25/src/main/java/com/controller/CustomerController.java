package com.controller;

import com.model.Customer;
import com.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }
}