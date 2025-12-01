package org.example.springuploadfile.repository;

import org.example.springuploadfile.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    boolean save(Product product);
    Product findById(int id);
    boolean update(int id, Product product);
    boolean remove(int id);
}
