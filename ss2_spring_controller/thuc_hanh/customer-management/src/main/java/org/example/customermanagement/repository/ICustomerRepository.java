package org.example.customermanagement.repository;

import org.example.customermanagement.entity.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
}
