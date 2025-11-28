package org.example.demo_spring_mvc.service;


import org.example.demo_spring_mvc.entity.Currency;
import org.example.demo_spring_mvc.repository.ICurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository repository;

    @Autowired
    public CurrencyService(ICurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public double convert(Currency currency) {
        return repository.convert(currency);
    }
}