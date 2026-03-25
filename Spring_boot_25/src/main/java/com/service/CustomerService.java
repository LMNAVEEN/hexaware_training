package com.service;

import com.model.Customer;
import com.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;

    public Customer addCustomer(Customer customer){
        return repo.save(customer);
    }
}