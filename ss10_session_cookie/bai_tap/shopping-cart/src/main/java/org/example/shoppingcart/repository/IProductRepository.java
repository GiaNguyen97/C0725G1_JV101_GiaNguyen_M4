package org.example.shoppingcart.repository;

import org.example.shoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
}
