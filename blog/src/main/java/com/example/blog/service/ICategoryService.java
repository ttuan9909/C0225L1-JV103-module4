package com.example.blog.service;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(int id);
    Category save(Category category);
    void deleteById(int id);
}
