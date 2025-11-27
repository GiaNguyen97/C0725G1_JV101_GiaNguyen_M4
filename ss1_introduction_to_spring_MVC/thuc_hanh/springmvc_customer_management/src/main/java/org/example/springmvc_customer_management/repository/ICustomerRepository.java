package org.example.springmvc_customer_management.repository;

import org.example.springmvc_customer_management.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();

    Customer findById(int id);
}
