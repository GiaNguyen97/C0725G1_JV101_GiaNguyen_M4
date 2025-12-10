package org.example.borrowbook.controller;

import org.example.borrowbook.dto.BorrowRecordDto;
import org.example.borrowbook.entity.Book;
import org.example.borrowbook.entity.BorrowRecord;
import org.example.borrowbook.exception.BookNotAvailableException;
import org.example.borrowbook.exception.InvalidBorrowCodeException;
import org.example.borrowbook.service.BookService;
import org.example.borrowbook.service.BorrowRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Thêm import này

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BorrowRecordService borrowService;

    public BookController(BookService bookService, BorrowRecordService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("books", bookService.listAll());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sách"));
        model.addAttribute("book", book);
        return "books/detail";
    }


    @PostMapping("/{id}/borrow")
    public String borrow(@PathVariable("id") Long id, RedirectAttributes redirect) {
        try {
            String code = borrowService.borrow(id);
            // Gửi thông báo thành công và mã mượn
            redirect.addFlashAttribute("successMessage",
                    "Mượn thành công! Vui lòng ghi lại mã: " + code);
            // Chuyển hướng về trang danh sách sách
            return "redirect:/books";
        } catch (BookNotAvailableException e) {
            // Gửi thông báo lỗi
            redirect.addFlashAttribute("errorMessage", "Mượn thất bại: Sách hiện đã hết.");
            // Chuyển hướng về trang chi tiết sách
            return "redirect:/books/" + id;
        }
    }


    @GetMapping("/return")
    public String returnForm() {
        // Vẫn trả về view 'books/return'
        return "books/return";
    }


    @PostMapping("/return")
    public String doReturn(@RequestParam("code") String code, RedirectAttributes redirect) {
        try {
            borrowService.returnBook(code);
            redirect.addFlashAttribute("successMessage", "Trả sách thành công!");
            // Trả sách thành công thì chuyển về trang danh sách
            return "redirect:/books";
        } catch (InvalidBorrowCodeException e) {
            redirect.addFlashAttribute("errorMessage",
                    "Trả sách thất bại: Mã mượn không hợp lệ hoặc đã được trả.");
            // Trả sách thất bại thì chuyển hướng ngược về form trả sách
            return "redirect:/books/return";
        }
    }

    @GetMapping("/borrowed")
    public String borrowedList(Model model) {
        List<BorrowRecord> records = borrowService.listCurrentBorrows();
        List<BorrowRecordDto> list = records.stream()
                .map(r -> new BorrowRecordDto(
                        r.getId(),
                        r.getBorrowCode(),
                        r.getBorrowedAt(),
                        r.getBook().getTitle()
                ))
                .toList();

        model.addAttribute("records", list);
        return "books/borrowed";
    }
}