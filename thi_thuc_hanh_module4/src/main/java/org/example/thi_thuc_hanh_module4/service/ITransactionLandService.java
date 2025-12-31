package org.example.thi_thuc_hanh_module4.service;

import org.example.thi_thuc_hanh_module4.entity.TransactionLand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITransactionLandService {
    TransactionLand save(TransactionLand transactionLand);

    TransactionLand update(TransactionLand transactionLand);

    void deleteById(Long id);

    Page<TransactionLand> search(String name, String service, Pageable pageable);

    TransactionLand findById(Long id);

    List<TransactionLand> findAll();
}
