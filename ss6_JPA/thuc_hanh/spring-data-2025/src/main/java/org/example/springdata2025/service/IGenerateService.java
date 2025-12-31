package org.example.springdata2025.service;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();

    boolean save(T t);

    T findById(Long id);

    boolean remove(Long id);
}