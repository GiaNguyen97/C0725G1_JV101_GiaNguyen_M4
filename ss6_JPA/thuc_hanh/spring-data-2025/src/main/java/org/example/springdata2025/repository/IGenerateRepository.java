package org.example.springdata2025.repository;

import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();

    T findById(Long id);

    boolean save(T t);

    boolean remove(Long id);
}