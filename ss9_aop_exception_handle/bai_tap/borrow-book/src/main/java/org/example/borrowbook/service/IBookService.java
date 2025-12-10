package org.example.borrowbook.service;

import org.example.borrowbook.entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    List<Book> listAll();

    Optional<Book> findById(Long id);

    Book save(Book b);
}
