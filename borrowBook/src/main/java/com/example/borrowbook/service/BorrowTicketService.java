package com.example.borrowbook.service;

import com.example.borrowbook.entity.Book;
import com.example.borrowbook.entity.BorrowTicket;
import com.example.borrowbook.repository.IBookRepository;
import com.example.borrowbook.repository.IBorrowTicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BorrowTicketService implements  IBookTicketService{
    private final IBorrowTicketRepository borrowTicketRepository;
    private final IBookRepository bookRepository;

    public BorrowTicketService(IBorrowTicketRepository borrowTicketRepository, IBookRepository bookRepository) {
        this.borrowTicketRepository = borrowTicketRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BorrowTicket createBorrowTicket(int bookId, String code) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        BorrowTicket ticket = new BorrowTicket();
        ticket.setBook(book);
        ticket.setCode(code);
        ticket.setBorrowedAt(LocalDateTime.now());
        return borrowTicketRepository.save(ticket);
    }

    @Override
    public void markAsReturned(String code) {
        BorrowTicket ticket = borrowTicketRepository.findByCode(code)
                .orElseThrow();
        ticket.setReturnedAt(LocalDateTime.now());
        borrowTicketRepository.save(ticket);
    }

    @Override
    public Optional<BorrowTicket> findActiveByCode(String code) {
        return borrowTicketRepository.findByCode(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return borrowTicketRepository.existsByCode(code);
    }
}
