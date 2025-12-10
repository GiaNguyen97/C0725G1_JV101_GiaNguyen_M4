package org.example.borrowbook.service;

import jakarta.transaction.Transactional;
import org.example.borrowbook.entity.Book;
import org.example.borrowbook.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final IBookRepository repo;

    public BookService(IBookRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Book> listAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    @Transactional
    public Book save(Book b) {
        return repo.save(b);
    }
}
