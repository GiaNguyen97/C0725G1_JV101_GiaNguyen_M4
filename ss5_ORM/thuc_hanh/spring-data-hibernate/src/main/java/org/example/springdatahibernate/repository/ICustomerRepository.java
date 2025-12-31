package org.example.springdatahibernate.repository;

import org.example.springdatahibernate.entity.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();

    boolean save(Customer customer);

    Customer findById(int id);

    boolean remove(int id);
}
