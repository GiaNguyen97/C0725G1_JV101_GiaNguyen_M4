package org.example.springmvc_customer_management.service;

import org.example.springmvc_customer_management.model.Customer;
import org.example.springmvc_customer_management.repository.CustomerRepository;
import org.example.springmvc_customer_management.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }
}
