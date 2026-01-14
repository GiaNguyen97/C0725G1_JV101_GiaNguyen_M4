package org.example.soccer_manager.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T findById(Integer id);
    boolean save(T t);
    boolean update(T t);
    boolean delete(Integer id);
}
