package org.example.thi_thuc_hanh_module_4_again.repository;

import org.example.thi_thuc_hanh_module_4_again.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
