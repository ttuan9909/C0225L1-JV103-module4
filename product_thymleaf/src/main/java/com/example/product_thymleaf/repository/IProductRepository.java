package com.example.product_thymleaf.repository;

import com.example.product_thymleaf.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(int id);
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(int id);
    Product findByName(String name);

}
