package com.example.blog.controller;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    // Danh sách bài viết
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    // Form tạo mới
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/form";
    }

    // Lưu bài viết
    @PostMapping("/save")
    public String save( @ModelAttribute("post") Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "posts/form";
        }
        if (post.getSummary() == null || post.getSummary().isBlank()) {
            post.setSummary(post.getContent().substring(0, Math.min(100, post.getContent().length())) + "...");
        }
        postService.save(post);
        return "redirect:/posts";
    }

    // Xem chi tiết
    @GetMapping("/{id}")
    public String view(@PathVariable int id, Model model) {
        Post post = postService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "posts/view";
    }

    // Cập nhật
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Post post = postService.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "posts/form";
    }

    // Xóa
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
