package org.example.springmvc_customer_management.service;

import org.example.springmvc_customer_management.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(int id);
}
