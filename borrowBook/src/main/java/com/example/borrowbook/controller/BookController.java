package com.example.borrowbook.controller;

import com.example.borrowbook.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var book = bookService.findById(id);
        model.addAttribute("book", book);
        return "books/detail";
    }

    @PostMapping("/{id}/borrow")
    public String borrow(@PathVariable Long id, RedirectAttributes ra) {
        String code = bookService.borrow(id);
        ra.addFlashAttribute("msg", "Mượn thành công! Mã: " + code);
        return "redirect:/books";
    }

    @GetMapping("/return")
    public String returnForm() { return "books/return"; }

    @PostMapping("/return")
    public String doReturn(@RequestParam String code, RedirectAttributes ra) {
        bookService.returnByCode(code.trim());
        ra.addFlashAttribute("msg", "Đã trả sách thành công!");
        return "redirect:/books";
    }
}
