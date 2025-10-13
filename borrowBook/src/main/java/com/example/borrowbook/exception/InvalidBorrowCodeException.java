package com.example.borrowbook.exception;

public class InvalidBorrowCodeException extends RuntimeException {
    public InvalidBorrowCodeException(String code) { super("Mã mượn " + code + " không hợp lệ!"); }
}
