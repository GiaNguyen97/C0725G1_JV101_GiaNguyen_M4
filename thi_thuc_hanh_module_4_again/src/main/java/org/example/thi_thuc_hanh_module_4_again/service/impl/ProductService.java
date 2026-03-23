package org.example.thi_thuc_hanh_module_4_again.service.impl;

import org.example.thi_thuc_hanh_module_4_again.entity.Product;
import org.example.thi_thuc_hanh_module_4_again.repository.ProductRepository;
import org.example.thi_thuc_hanh_module_4_again.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
