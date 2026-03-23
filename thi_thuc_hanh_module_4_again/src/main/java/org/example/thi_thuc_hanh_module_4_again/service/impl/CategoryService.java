package org.example.thi_thuc_hanh_module_4_again.service.impl;

import org.example.thi_thuc_hanh_module_4_again.entity.Category;
import org.example.thi_thuc_hanh_module_4_again.repository.CategoryRepository;
import org.example.thi_thuc_hanh_module_4_again.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
