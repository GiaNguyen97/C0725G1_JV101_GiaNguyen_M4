package org.example.product_manager_thymeleaf.service;

import org.example.product_manager_thymeleaf.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(int id);
    boolean save(Product product);
    boolean update(int id, Product product);
    boolean delete(int id);
    List<Product> searchByName(String name);
}
