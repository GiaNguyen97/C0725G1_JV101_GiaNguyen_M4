package org.example.my_blog.service;

import org.example.my_blog.entity.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    Blog findById(Long id);
    Blog save(Blog blog);
    boolean delete(Long id);
}
