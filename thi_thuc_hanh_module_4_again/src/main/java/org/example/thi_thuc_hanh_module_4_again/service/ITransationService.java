package org.example.thi_thuc_hanh_module_4_again.service;

import org.example.thi_thuc_hanh_module_4_again.entity.Transation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface ITransationService {

    Page<Transation> findAll(LocalDate dateFrom, LocalDate dateTo, Pageable pageable);

    // Lưu sản phẩm (Thêm mới hoặc Cập nhật)
    void save(Transation product);

    // Tìm theo ID
    Optional<Transation> findById(Long id);

    // Xóa theo ID
    void deleteById(Long id);
}
