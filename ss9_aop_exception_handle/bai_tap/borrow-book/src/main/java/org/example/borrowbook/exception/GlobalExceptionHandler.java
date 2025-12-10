package org.example.borrowbook.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotAvailableException.class)
    public String handleBookNotAvailable(BookNotAvailableException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // thymeleaf error.html
    }

    @ExceptionHandler(InvalidBorrowCodeException.class)
    public String handleInvalidCode(InvalidBorrowCodeException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOther(Exception ex, Model model) {
        model.addAttribute("error", "Lỗi hệ thống: " + ex.getMessage());
        return "error";
    }
}

