package com.example.product_thymleaf.service;

import com.example.product_thymleaf.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(int id);
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(int id);
    Product findByName(String name);
}
