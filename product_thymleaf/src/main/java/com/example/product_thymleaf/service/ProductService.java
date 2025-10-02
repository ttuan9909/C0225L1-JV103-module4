package com.example.product_thymleaf.service;

import com.example.product_thymleaf.entity.Product;
import com.example.product_thymleaf.repository.IProductRepository;
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
        return productRepository.findAll();    }

    @Override
    public Product findById(int id) {
        if (id <= 0) return null;
        return productRepository.findById(id);
    }

    @Override
    public boolean add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public boolean update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public boolean delete(int id) {
        return productRepository.delete(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
}
