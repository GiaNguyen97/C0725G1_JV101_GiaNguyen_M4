package org.example.demo_spring_mvc.repository;

import org.example.demo_spring_mvc.entity.Word;

import java.util.Collection;

public interface IDictionaryRepository {
    Word findByKey(String key);
    Collection<Word> findAll();
    boolean save(Word w);
}
