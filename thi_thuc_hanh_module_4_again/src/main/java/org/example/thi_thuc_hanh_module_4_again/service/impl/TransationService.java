package org.example.thi_thuc_hanh_module_4_again.service.impl;

import org.example.thi_thuc_hanh_module_4_again.entity.Transation;
import org.example.thi_thuc_hanh_module_4_again.repository.TransationRepository;
import org.example.thi_thuc_hanh_module_4_again.service.ITransationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransationService implements ITransationService {

    @Autowired
    private TransationRepository transationRepository;

    @Override
    public Page<Transation> findAll(LocalDate dateFrom, LocalDate dateTo, Pageable pageable) {
        // Gọi custom query từ repository
        return transationRepository.searchTransations( dateFrom, dateTo, pageable);
    }

    @Override
    public void save(Transation transation) {
        transationRepository.save(transation);
    }

    @Override
    public Optional<Transation> findById(Long id) {
        return transationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        transationRepository.deleteById(id);
    }



}
