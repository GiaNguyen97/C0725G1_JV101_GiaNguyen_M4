package org.example.soccer_manager.exception;

public class MaxPlayerExceededException extends RuntimeException {

    public MaxPlayerExceededException() {
        super("Đội hình đã đủ 11 cầu thủ thi đấu");
    }
}