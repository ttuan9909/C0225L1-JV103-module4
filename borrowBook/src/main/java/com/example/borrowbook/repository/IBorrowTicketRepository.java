package com.example.borrowbook.repository;

import com.example.borrowbook.entity.Book;
import com.example.borrowbook.entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBorrowTicketRepository extends JpaRepository<BorrowTicket, Integer> {
    Optional<BorrowTicket> findByCode(String code);
    boolean existsByCode(String code);
}
