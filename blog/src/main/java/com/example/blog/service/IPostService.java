package com.example.blog.service;

import com.example.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    Page<Post> findAll(Pageable pageable);
    Optional<Post> findById(int id);
    Post save(Post post);
    void deleteById(int id);
    Page<Post> search(String title, Pageable pageable);
}
