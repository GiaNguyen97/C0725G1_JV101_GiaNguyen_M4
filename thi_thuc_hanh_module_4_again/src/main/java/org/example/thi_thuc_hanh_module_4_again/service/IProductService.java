package org.example.thi_thuc_hanh_module_4_again.service;

import org.example.thi_thuc_hanh_module_4_again.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IProductService {
    List<Product> findAll();
}
