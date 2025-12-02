package org.example.product_manager_thymeleaf.repository;

import org.example.product_manager_thymeleaf.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(int id);
    boolean save(Product product);
    boolean update(int id, Product product);
    boolean delete(int id);
    List<Product> searchByName(String name);
}
