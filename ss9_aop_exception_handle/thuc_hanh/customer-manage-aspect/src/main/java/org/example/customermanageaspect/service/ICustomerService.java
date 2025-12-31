package org.example.customermanageaspect.service;

import org.example.customermanageaspect.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll() throws Exception;

    Customer findOne(Long id) throws Exception;
}