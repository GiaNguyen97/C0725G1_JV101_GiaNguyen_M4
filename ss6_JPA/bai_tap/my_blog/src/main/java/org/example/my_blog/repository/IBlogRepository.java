package org.example.my_blog.repository;

import org.example.my_blog.entity.Blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IBlogRepository extends JpaRepository <Blog, Long> {
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findByTitleContaining(String keyword, Pageable pageable);

    Page<Blog> findByCategory_Id(Long categoryId, Pageable pageable);
}
