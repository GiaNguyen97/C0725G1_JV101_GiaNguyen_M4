package org.example.thi_thuc_hanh_module_4_again.repository;

import org.example.thi_thuc_hanh_module_4_again.entity.Transation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;

@Repository
public interface TransationRepository extends JpaRepository<Transation, Long> {

    @Query("SELECT t FROM Transation t WHERE " +
            "(:dateFrom IS NULL OR t.buyDate >= :dateFrom) AND " +
            "(:dateTo IS NULL OR t.buyDate <= :dateTo)")
    Page<Transation> searchTransations(@Param("dateFrom") LocalDate dateFrom,
                                 @Param("dateTo") LocalDate dateTo,
                                 Pageable pageable);

}
