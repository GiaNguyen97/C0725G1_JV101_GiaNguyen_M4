package org.example.demo_spring_mvc.service;

import org.example.demo_spring_mvc.entity.Word;
import org.example.demo_spring_mvc.repository.IDictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DictionaryService implements IDictionaryService{
    private final IDictionaryRepository dictionaryRepository;
    public DictionaryService(IDictionaryRepository iDictionaryRepository) {
        this.dictionaryRepository = iDictionaryRepository;
    }
    @Override
    public Word findByKey(String key) {
        return dictionaryRepository.findByKey(key);
    }
    @Override
    public Collection<Word> findAll() {
        return dictionaryRepository.findAll();
    }
    @Override
    public boolean save(Word w) {
        return dictionaryRepository.save(w);
    }
}
