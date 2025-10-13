package com.example.borrowbook.service;

import com.example.borrowbook.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    String borrow(Long bookId);
    void returnByCode(String code);
    Book findById(Long id);
}
