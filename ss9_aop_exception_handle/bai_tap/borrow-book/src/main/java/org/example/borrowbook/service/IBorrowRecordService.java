package org.example.borrowbook.service;

import org.example.borrowbook.entity.BorrowRecord;
import org.example.borrowbook.exception.BookNotAvailableException;
import org.example.borrowbook.exception.InvalidBorrowCodeException;

import java.util.List;

public interface IBorrowRecordService {

    String borrow(Long bookId) throws BookNotAvailableException;

    void returnBook(String borrowCode) throws InvalidBorrowCodeException;

    List<BorrowRecord> listCurrentBorrows();
}
