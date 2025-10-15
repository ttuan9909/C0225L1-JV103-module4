package com.example.blog.dto;

import com.example.blog.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
        private int id;
        private String title;
        private String content;
        private String summary;
        private LocalDateTime createdAt = LocalDateTime.now();
        private CategoryDto categoryDto;
}
