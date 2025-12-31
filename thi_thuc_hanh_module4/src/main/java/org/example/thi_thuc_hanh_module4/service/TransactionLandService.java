package org.example.thi_thuc_hanh_module4.service;

import lombok.RequiredArgsConstructor;
import org.example.thi_thuc_hanh_module4.entity.TransactionLand;
import org.example.thi_thuc_hanh_module4.repository.TransactionLandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionLandService implements ITransactionLandService{
    private final TransactionLandRepository transactionLandRepository;


    @Override
    public TransactionLand save(TransactionLand transactionLand) {
        return transactionLandRepository.save(transactionLand);
    }

    @Override
    public TransactionLand update(TransactionLand transactionLand) {
        return transactionLandRepository.save(transactionLand);
    }

    @Override
    public void deleteById(Long id) {
        transactionLandRepository.deleteById(id);
    }

    @Override
    public Page<TransactionLand> search( String name, String service, Pageable pageable) {
        return transactionLandRepository.search(name,service,pageable);
    }


    @Override
    public TransactionLand findById(Long id) {
        return transactionLandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy heo với ID: " + id));
    }

    @Override
    public List<TransactionLand> findAll() {
        return transactionLandRepository.findAll();
    }
}
