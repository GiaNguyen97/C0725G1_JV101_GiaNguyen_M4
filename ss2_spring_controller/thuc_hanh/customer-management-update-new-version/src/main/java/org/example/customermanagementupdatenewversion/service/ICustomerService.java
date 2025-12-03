package org.example.customermanagementupdatenewversion.service;

import org.example.customermanagementupdatenewversion.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
}
