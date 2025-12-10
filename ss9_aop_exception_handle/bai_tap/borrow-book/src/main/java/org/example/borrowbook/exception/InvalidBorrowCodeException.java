package org.example.borrowbook.exception;

public class InvalidBorrowCodeException extends Exception{
    public InvalidBorrowCodeException(String message) {
        super(message);
    }
}
