package org.example.springuploadfile.service;

import org.example.springuploadfile.entity.Product;
import org.example.springuploadfile.repository.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public java.util.List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean update(int id, Product product) {
       return productRepository.update(id, product);
    }

    @Override
    public boolean remove(int id) {
       return productRepository.remove(id);
    }
}
