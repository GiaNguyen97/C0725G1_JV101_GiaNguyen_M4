package org.example.springuploadfile.repository;

import org.example.springuploadfile.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductRepository implements IProductRepository{
    private final List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public boolean save(Product product) {
        return products.add(product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public boolean update(int id, Product product) {
        int index = products.indexOf(findById(id));
        products.set(index, product);
        return true;
    }

    @Override
    public boolean remove(int id) {
       return products.remove(findById(id));
    }
}
