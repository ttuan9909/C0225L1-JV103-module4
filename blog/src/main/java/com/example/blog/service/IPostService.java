package com.example.blog.service;

import com.example.blog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> findAll();
    Optional<Post> findById(int id);
    Post save(Post post);
    void deleteById(int id);
}
