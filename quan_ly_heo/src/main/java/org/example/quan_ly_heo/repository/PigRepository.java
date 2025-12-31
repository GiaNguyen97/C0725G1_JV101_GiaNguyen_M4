package org.example.quan_ly_heo.repository;

import org.example.quan_ly_heo.entity.Pig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PigRepository extends JpaRepository<Pig, Long> {

    @Query("""
        SELECT p FROM Pig p
        WHERE (:sold IS NULL OR 
              (:sold = true AND p.exportTime IS NOT NULL) OR
              (:sold = false AND p.exportTime IS NULL))
        AND (:code IS NULL OR p.code LIKE %:code%)
        AND (:originId IS NULL OR p.origin.id = :originId)
    """)
    Page<Pig> search(
            @Param("sold") Boolean sold,
            @Param("code") String code,
            @Param("originId") Long originId,
            Pageable pageable
    );

    List<Pig> findTop10ByExportTimeIsNotNullOrderByExportWeightDesc();
    List<Pig> findTop20ByExportTimeIsNotNullOrderByExportWeightDesc();
}