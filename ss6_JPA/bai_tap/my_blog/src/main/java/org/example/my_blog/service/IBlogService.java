package org.example.my_blog.service;

import org.example.my_blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findByTitleContaining(String keyword, Pageable pageable);
    Page<Blog> findByCategory_Id(Long categoryId, Pageable pageable);
    Blog findById(Long id);
    Blog save(Blog blog);
    boolean delete(Long id);
}
