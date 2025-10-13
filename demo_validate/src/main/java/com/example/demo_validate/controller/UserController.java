package com.example.demo_validate.controller;

import com.example.demo_validate.dto.UserDto;
import com.example.demo_validate.entity.User;
import com.example.demo_validate.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("create")
    public String createForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "users/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("userDto") @Valid UserDto userDto,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            return "users/form";
        }
        userService.save(userDto);
        model.addAttribute("fullName", userDto.getFirstName() + " " + userDto.getLastName());
        return "redirect:/users";
    }
}
