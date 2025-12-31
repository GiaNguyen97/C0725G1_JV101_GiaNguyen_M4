package org.example.springdatahibernate.service;

import org.example.springdatahibernate.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    boolean save(Customer customer);

    Customer findById(int id);

    boolean remove(int id);
}
