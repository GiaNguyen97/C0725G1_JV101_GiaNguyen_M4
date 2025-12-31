package org.example.thi_thuc_hanh_module4.repository;

import org.example.thi_thuc_hanh_module4.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
