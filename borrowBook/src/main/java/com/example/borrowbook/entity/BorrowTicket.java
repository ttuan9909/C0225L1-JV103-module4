package com.example.borrowbook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BorrowTicket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    private Book book;

    @Column(nullable=false, length=5, unique=true)
    private String code;

    @Column(nullable=false)
    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;
}
