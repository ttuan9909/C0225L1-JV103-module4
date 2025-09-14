package com.example.dictionary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class DictionaryController {

    private static final Map<String, String> DICT = Map.of(
            "hello", "xin chào",
            "world", "thế giới",
            "computer", "máy tính",
            "spring", "mùa xuân / Spring framework",
            "book", "quyển sách",
            "cat", "con mèo",
            "dog", "con chó"
    );

    @GetMapping({"/dictionary", "/home"})
    public String showForm() {
        return "index";
    }

    @GetMapping("/lookup")
    public String lookup(@RequestParam("q") String q, Model model) {
        String key = q == null ? "" : q.trim().toLowerCase();
        String meaning = DICT.get(key);
        model.addAttribute("query", q);

        if (meaning != null) {
            model.addAttribute("found", true);
            model.addAttribute("meaning", meaning);
        } else {
            model.addAttribute("found", false);
            model.addAttribute("message", "Không tìm thấy từ cần tra.");
        }
        return "index"; // hiển thị kết quả trên cùng trang
    }
}
