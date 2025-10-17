package com.example.blog.controller;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.service.CategoryService;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;

    // Danh sách bài viết
//    @GetMapping
//    public String list(Model model) {
//        model.addAttribute("posts", postService.findAll());
//        return "posts/list";
//    }

    @GetMapping(value = "")
    public ModelAndView showList(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                 @RequestParam(name = "searchTitle", required = false,defaultValue = "")String title){
        Pageable pageable = PageRequest.of(page,3,Sort.by("title").descending());

        ModelAndView modelAndView = new ModelAndView("/posts/list");
        Page<Post> postPage = postService.search(title,pageable);
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("searchTitle", title);
        return modelAndView;
    }

    // Form tạo mới
    @GetMapping("/create")
    public String createForm(Model model) {
        Post post = new Post();
        post.setCategory(new Category());
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.findAll());
        return "posts/form";
    }

    // Lưu bài viết
    @PostMapping("/save")
    public String save(@ModelAttribute("post") Post post, BindingResult result, Model model) {
        // validate category
        if (post.getCategory() == null || post.getCategory().getId() <= 0) {
            result.rejectValue("category", "invalid.category", "Vui lòng chọn danh mục");
        }
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "posts/form";
        }

        int catId = post.getCategory().getId();
        Category cat = categoryService.findById(catId).orElseThrow();
        post.setCategory(cat);

        if (post.getSummary() == null || post.getSummary().isBlank()) {
            String c = post.getContent() != null ? post.getContent() : "";
            post.setSummary(c.substring(0, Math.min(100, c.length())) + (c.length() > 100 ? "..." : ""));
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
        model.addAttribute("categories", categoryService.findAll());
        return "posts/form";
    }

    // Xóa
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
