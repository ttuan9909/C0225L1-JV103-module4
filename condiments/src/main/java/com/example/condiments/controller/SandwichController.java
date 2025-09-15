package com.example.condiments.controller;

import com.example.condiments.entity.Selection;
import com.example.condiments.service.ISelectionService;
import com.example.condiments.service.SelectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class SandwichController {
    private final ISelectionService selectionService;

    public SandwichController(ISelectionService selectionService) {
        this.selectionService = selectionService;
    }
    @GetMapping({"/", "/home"})
    public String showForm() {
        return "index";
    }

    @PostMapping("/save")
    public String save(@RequestParam("condiment") String[] condiment, Model model) {
        List<String> condiments = Arrays.asList(condiment);

        Selection selection = selectionService.save(condiments);

        model.addAttribute("selection", selection);
        model.addAttribute("condiments", condiments);

        return "result";
    }
}
