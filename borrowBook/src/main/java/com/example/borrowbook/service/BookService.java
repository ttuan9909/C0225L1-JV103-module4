package com.example.borrowbook.service;

import com.example.borrowbook.entity.Book;
import com.example.borrowbook.entity.BorrowTicket;
import com.example.borrowbook.exception.InvalidBorrowCodeException;
import com.example.borrowbook.exception.OutOfStockException;
import com.example.borrowbook.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookService implements IBookService{
    private final IBookRepository bookRepository;
    private final BorrowTicketService borrowTicketService;

    public BookService(IBookRepository bookRepository, BorrowTicketService borrowTicketService) {
        this.bookRepository = bookRepository;
        this.borrowTicketService = borrowTicketService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public String borrow(Long bookId) {
        Book book = findById(bookId);
        if (book.getAvailableQuantity() <= 0)
            throw new OutOfStockException(bookId);

        String code = generateCode();
        borrowTicketService.createBorrowTicket(Math.toIntExact(bookId), code);
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        return code;
    }

    @Override
    public void returnByCode(String code) {
        BorrowTicket ticket = borrowTicketService.findActiveByCode(code)
                .orElseThrow(() -> new InvalidBorrowCodeException(code));

        borrowTicketService.markAsReturned(code);
        Book book = ticket.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sách ID = " + id));
    }

    private String generateCode() {
        String code;
        do {
            code = String.format("%05d", ThreadLocalRandom.current().nextInt(0, 100000));
        } while (borrowTicketService.existsByCode(code));
        return code;
    }
}
