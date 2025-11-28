package org.example.demo_spring_mvc.repository;

import org.example.demo_spring_mvc.entity.Currency;

public interface ICurrencyRepository {
    double convert(Currency currency);
}
