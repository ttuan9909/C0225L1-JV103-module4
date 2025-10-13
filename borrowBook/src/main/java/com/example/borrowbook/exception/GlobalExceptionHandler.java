package com.example.borrowbook.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({OutOfStockException.class, InvalidBorrowCodeException.class})
    public String handleBusiness(RuntimeException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/error"; // 1 view báo lỗi nghiệp vụ
    }

    @ExceptionHandler(Exception.class)
    public String handleOther(Exception ex, Model model) {
        log.error("Unexpected error", ex);
        model.addAttribute("error", "Đã có lỗi xảy ra. Vui lòng thử lại!");
        return "error/error";
    }
}
