package com.example.demo_session_cookie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
}
