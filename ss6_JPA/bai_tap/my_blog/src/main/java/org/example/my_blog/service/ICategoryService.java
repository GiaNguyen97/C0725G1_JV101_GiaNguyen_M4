package org.example.my_blog.service;

import org.example.my_blog.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void delete(Long id);
}
