package org.example.thi_thuc_hanh_module4.repository;

import org.example.thi_thuc_hanh_module4.entity.TransactionLand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionLandRepository extends JpaRepository<TransactionLand, Long> {

    @Query("""
        SELECT t FROM TransactionLand t
        WHERE  (:name IS NULL OR t.customer.name LIKE %:name%)
        AND (:service IS NULL OR :service = '' OR t.service = :service)
    """)
    Page<TransactionLand> search(
            @Param("name") String name,
            @Param("service") String service,
            Pageable pageable
    );
}
