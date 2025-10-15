package com.example.demo_session_cookie.repository;

import com.example.demo_session_cookie.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<Product, Integer> {
}
