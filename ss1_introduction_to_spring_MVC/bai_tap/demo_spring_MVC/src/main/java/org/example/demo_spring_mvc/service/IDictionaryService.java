package org.example.demo_spring_mvc.service;

import org.example.demo_spring_mvc.entity.Word;

import java.util.Collection;

public interface IDictionaryService {
    Word findByKey(String key);
    Collection<Word> findAll();
    boolean save(Word w);
}
