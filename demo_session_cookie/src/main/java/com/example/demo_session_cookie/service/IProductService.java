package com.example.demo_session_cookie.service;

import com.example.demo_session_cookie.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(int id);
}
