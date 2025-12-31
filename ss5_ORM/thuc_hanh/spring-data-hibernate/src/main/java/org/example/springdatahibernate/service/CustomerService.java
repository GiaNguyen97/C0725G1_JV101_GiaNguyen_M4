package org.example.springdatahibernate.service;

import org.example.springdatahibernate.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public java.util.List<org.example.springdatahibernate.entity.Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public boolean save(org.example.springdatahibernate.entity.Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public org.example.springdatahibernate.entity.Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public boolean remove(int id) {
        return customerRepository.remove(id);
    }
}
