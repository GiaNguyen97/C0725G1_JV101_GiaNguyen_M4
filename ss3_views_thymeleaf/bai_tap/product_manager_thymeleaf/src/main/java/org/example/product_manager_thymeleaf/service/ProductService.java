package org.example.product_manager_thymeleaf.service;

import org.example.product_manager_thymeleaf.entity.Product;
import org.example.product_manager_thymeleaf.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private int generateId() {
        List<Product> products = productRepository.findAll();
        int maxId = 0;

        for (Product p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }

        return maxId + 1;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean save(Product product) {
        product.setId(generateId());
        return productRepository.save(product);
    }

    @Override
    public boolean update(int id, Product product) {
        return productRepository.update(id, product);
    }

    @Override
    public boolean delete(int id) {
        return productRepository.delete(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }
}
