package org.example.borrowbook.service;

import org.example.borrowbook.aspect.StateChanging;
import org.example.borrowbook.entity.BorrowRecord;
import org.example.borrowbook.entity.Book;
import org.example.borrowbook.exception.BookNotAvailableException;
import org.example.borrowbook.exception.InvalidBorrowCodeException;
import org.example.borrowbook.repository.IBookRepository;
import org.example.borrowbook.repository.IBorrowRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class BorrowRecordService implements IBorrowRecordService {

    private final IBookRepository bookRepo;
    private final IBorrowRecordRepository borrowRepo;
    private final Random random = new Random();

    public BorrowRecordService(IBookRepository bookRepo,
                               IBorrowRecordRepository borrowRepo) {
        this.bookRepo = bookRepo;
        this.borrowRepo = borrowRepo;
    }

    // Tạo mã mượn ngẫu nhiên 5 số
    private String generateCode() {
        int code = 10000 + random.nextInt(90000);
        return String.valueOf(code);
    }

    // ==============================
    //   MƯỢN SÁCH
    // ==============================
    @Override
    @StateChanging("Muợn sách")
    @Transactional
    public String borrow(Long bookId) throws BookNotAvailableException {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sách"));

        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Sách đã hết, không thể mượn.");
        }

        // giảm số lượng
        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);

        // tạo record mượn
        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setBorrowCode(generateCode());
        record.setBorrowedAt(LocalDateTime.now());

        borrowRepo.save(record);

        return record.getBorrowCode();
    }

    // ==============================
    //   TRẢ SÁCH
    // ==============================
    @Override
    @StateChanging("Trả sách")
    @Transactional
    public void returnBook(String borrowCode) throws InvalidBorrowCodeException {

        BorrowRecord record = borrowRepo.findByBorrowCode(borrowCode)
                .orElseThrow(() -> new InvalidBorrowCodeException("Mã mượn không hợp lệ hoặc đã trả."));

        Book book = record.getBook();
        if (book == null) {
            throw new IllegalArgumentException("Sách liên quan không tồn tại");
        }

        // tăng lại số lượng
        book.setQuantity(book.getQuantity() + 1);
        bookRepo.save(book);

        // cập nhật record
        record.setReturnedAt(LocalDateTime.now());
        borrowRepo.save(record);
    }

    // ==============================
    //   DANH SÁCH ĐANG MƯỢN
    // ==============================
    @Override
    public List<BorrowRecord> listCurrentBorrows() {
        return borrowRepo.findAllByReturnedAtIsNull();
    }
}
