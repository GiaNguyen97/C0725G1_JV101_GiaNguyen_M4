package org.example.customermanagementupdatenewversion.repository;

import org.example.customermanagementupdatenewversion.entity.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
}
