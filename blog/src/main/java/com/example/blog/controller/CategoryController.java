package com.example.blog.controller;

import com.example.blog.entity.Category;
import com.example.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/form";
    }

    @PostMapping("/save")
    public String save( @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/form";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable int id, Model model) {
        Category category = categoryService.findById(id).orElseThrow();
        model.addAttribute("category", category);
        return "categories/view";
    }

    // Cập nhật
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Category category = categoryService.findById(id).orElseThrow();
        model.addAttribute("category", category);
        return "categories/form";
    }

    // Xóa
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
