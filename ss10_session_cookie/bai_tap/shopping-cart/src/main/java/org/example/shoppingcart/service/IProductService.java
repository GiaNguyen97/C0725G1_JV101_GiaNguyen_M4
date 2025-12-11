package org.example.shoppingcart.service;

import org.example.shoppingcart.entity.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);
}