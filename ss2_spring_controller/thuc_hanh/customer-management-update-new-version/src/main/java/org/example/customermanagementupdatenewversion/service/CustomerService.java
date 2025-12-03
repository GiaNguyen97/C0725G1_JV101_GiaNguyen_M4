package org.example.customermanagementupdatenewversion.service;

import org.example.customermanagementupdatenewversion.repository.CustomerRepository;
import org.example.customermanagementupdatenewversion.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

    private ICustomerRepository customerRepository;
    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    @Override
    public java.util.List<org.example.customermanagementupdatenewversion.entity.Customer> findAll() {
        return customerRepository.findAll();}
    @Override
    public org.example.customermanagementupdatenewversion.entity.Customer findById(Long id) {
        return customerRepository.findById(id);}
    @Override
    public void save(org.example.customermanagementupdatenewversion.entity.Customer customer) {
        customerRepository.save(customer);}
}
