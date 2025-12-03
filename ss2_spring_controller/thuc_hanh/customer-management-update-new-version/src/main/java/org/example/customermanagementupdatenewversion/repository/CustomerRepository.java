package org.example.customermanagementupdatenewversion.repository;

import org.example.customermanagementupdatenewversion.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository implements ICustomerRepository{
    private static Map<Long, Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1L, new Customer(1L, "Nguyen Khac Nhat", "nhat@codegym.vn", "Ha Noi"));
        customers.put(2L, new Customer(2L, "Dang Huy Hoa", "hoa.dang@codegym.vn", "Da Nang"));
        customers.put(3L, new Customer(3L, "Le Thi Chau", "chau.le@codegym.vn", "Ha Noi"));
        customers.put(4L, new Customer(4L, "Nguyen Thuy Duong", "duong.nguyen@codegym.vn", "Sai Gon"));
        customers.put(5L, new Customer(5L, "CodeGym", "codegym@codegym.vn", "Viet Nam"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer findById(Long id) {
        return customers.get(id);
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}