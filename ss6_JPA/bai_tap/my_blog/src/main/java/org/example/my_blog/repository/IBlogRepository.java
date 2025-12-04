package org.example.my_blog.repository;

import org.example.my_blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository <Blog, Long> {
}
