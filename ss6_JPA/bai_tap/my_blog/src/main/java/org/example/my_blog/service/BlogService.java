package org.example.my_blog.service;

import org.example.my_blog.entity.Blog;
import org.example.my_blog.repository.IBlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class BlogService implements IBlogService {
    private final IBlogRepository blogRepository;

    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findByTitleContaining(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContaining(keyword,pageable);
    }

    @Override
    public Page<Blog> findByCategory_Id(Long categoryId, Pageable pageable) {
        return blogRepository.findByCategory_Id(categoryId,pageable);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public boolean delete(Long id) {
        try {
            blogRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
