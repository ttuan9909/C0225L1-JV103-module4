package com.example.demo_session_cookie.service;

import com.example.demo_session_cookie.entity.Product;
import com.example.demo_session_cookie.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }
}
