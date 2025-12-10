package org.example.borrowbook.repository;

import org.example.borrowbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,Long> {
}
