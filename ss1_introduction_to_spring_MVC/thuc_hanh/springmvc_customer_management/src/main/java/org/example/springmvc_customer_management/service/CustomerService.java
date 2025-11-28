package org.example.springmvc_customer_management.service;

import org.example.springmvc_customer_management.model.Customer;
import org.example.springmvc_customer_management.repository.ICustomerRepository;

import java.util.List;

public class CustomerService implements ICustomerService{

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }
}
