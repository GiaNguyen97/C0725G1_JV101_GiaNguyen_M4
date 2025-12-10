package org.example.borrowbook.repository;

import org.example.borrowbook.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findByBorrowCode(String borrowCode);
    List<BorrowRecord> findAllByReturnedAtIsNull();
}
