package com.example.borrowbook.service;

import com.example.borrowbook.entity.BorrowTicket;

import java.util.Optional;

public interface IBookTicketService {
    BorrowTicket createBorrowTicket(int bookId, String code);
    void markAsReturned(String code);
    Optional<BorrowTicket> findActiveByCode(String code);
    boolean existsByCode(String code);
}
