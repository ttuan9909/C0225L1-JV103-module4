package com.example.blog.controller;

import com.example.blog.dto.CategoryDto;
import com.example.blog.dto.PostDto;
import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.service.ICategoryService;
import com.example.blog.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/api/posts")
public class RestPostController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IPostService postService;

    private PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);

        if (post.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(post.getCategory(), categoryDto);
            postDto.setCategoryDto(categoryDto);
        }
        return postDto;
    }

    @GetMapping("")
    public ResponseEntity<List<PostDto>> getAll() {
        List<Post> posts = postService.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PostDto> postDtos = posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> detail(@PathVariable int id) {
        Optional<Post> post = postService.findById(id);
        if (post == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PostDto postDto = convertToDto(post.get());
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PostDto> add(@RequestBody PostDto postDto,
                                    BindingResult bindingResult) {
        Map<String, String> map = new LinkedHashMap<>();
        if (bindingResult.hasErrors()) {
            map.put("title","Tiêu đề không được để trống");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postDto, post, "id", "categoryDto");
        Optional<Category> categoryOptional = categoryService.findById(postDto.getCategoryDto().getId());
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        post.setCategory(categoryOptional.get());
        Post savedPost = postService.save(post);
        return new ResponseEntity<>(convertToDto(savedPost), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable(name = "id") int id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id,
                                    @RequestBody PostDto postDto) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post existingPost = post.get();
        BeanUtils.copyProperties(postDto, existingPost, "id", "categoryDto");
        if (postDto.getCategoryDto() != null) {
            Optional<Category> categoryOptional = categoryService.findById(postDto.getCategoryDto().getId());
            if (!categoryOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            existingPost.setCategory(categoryOptional.get());
        }
        postService.save(existingPost);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
