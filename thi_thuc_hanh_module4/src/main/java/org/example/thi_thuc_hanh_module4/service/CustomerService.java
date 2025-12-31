package org.example.thi_thuc_hanh_module4.service;

import lombok.RequiredArgsConstructor;
import org.example.thi_thuc_hanh_module4.entity.Customer;
import org.example.thi_thuc_hanh_module4.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
