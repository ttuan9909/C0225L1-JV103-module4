package com.example.caculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping({"/", "/home"})
    public String showForm() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model) {

        double result = 0;
        String message = "";

        switch (operation) {
            case "add":
                result = num1 + num2;
                message = "Addition (+)";
                break;
            case "sub":
                result = num1 - num2;
                message = "Subtraction (-)";
                break;
            case "mul":
                result = num1 * num2;
                message = "Multiplication (×)";
                break;
            case "div":
                if (num2 == 0) {
                    model.addAttribute("error", "Không thể chia cho 0!");
                    return "index";
                }
                result = num1 / num2;
                message = "Division (/)";
                break;
        }

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operation", message);
        model.addAttribute("result", result);

        return "index";
    }
}