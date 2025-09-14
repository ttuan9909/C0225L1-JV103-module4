package com.example.demo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {

    // Trang chủ: hiển thị form
    @GetMapping({"/", "/home"})
    public String showForm() {
        return "index";
    }

    // Nhận dữ liệu và tính
    @PostMapping("/convert")
    public String convert(
            @RequestParam("rate") String rateStr,
            @RequestParam("usd") String usdStr,
            Model model) {

        try {
            double rate = Double.parseDouble(rateStr);
            double usd  = Double.parseDouble(usdStr);

            if (rate <= 0 || usd < 0) {
                model.addAttribute("error", "Tỉ giá phải > 0 và USD ≥ 0.");
                return "index";
            }

            double vnd = rate * usd;

            model.addAttribute("rate", rate);
            model.addAttribute("usd", usd);
            model.addAttribute("vnd", vnd);
            return "result";

        } catch (NumberFormatException e) {
            model.addAttribute("error", "Vui lòng nhập số hợp lệ.");
            return "index";
        }
    }
}