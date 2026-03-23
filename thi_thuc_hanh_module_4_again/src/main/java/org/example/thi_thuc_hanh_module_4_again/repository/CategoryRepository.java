package org.example.thi_thuc_hanh_module_4_again.repository;

import org.example.thi_thuc_hanh_module_4_again.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
