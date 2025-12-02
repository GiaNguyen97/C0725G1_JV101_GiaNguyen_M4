package org.example.customermanagement.service;

import org.example.customermanagement.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
}
