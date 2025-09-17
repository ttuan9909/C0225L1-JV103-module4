package com.example.mailsetting.controller;

import com.example.mailsetting.entity.MailSetting;
import com.example.mailsetting.service.ISettingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class SettingController {
    private final ISettingService settingService;

    private static final List<String> ALLOWED_LANGUAGES =
            List.of("English", "Vietnamese", "Japanese", "Chinese");
    private static final List<Integer> ALLOWED_SIZES =
            List.of(5, 10, 15, 25, 50, 100);

    @ModelAttribute("languages")
    public List<String> languages() { return ALLOWED_LANGUAGES; }

    @ModelAttribute("pageSizes")
    public List<Integer> pageSizes() { return ALLOWED_SIZES; }

    public SettingController(ISettingService settingService) {
        this.settingService = settingService;
    }

    @PostMapping
    public String update(@ModelAttribute("settings") MailSetting form,
                         BindingResult binding,
                         Model model) {

        if (form.getLanguage() == null || !ALLOWED_LANGUAGES.contains(form.getLanguage())) {
            binding.rejectValue("language", "invalid.language", "Ngôn ngữ không hợp lệ");
        }
        if (form.getPageSize() == null || !ALLOWED_SIZES.contains(form.getPageSize())) {
            binding.rejectValue("pageSize", "invalid.pageSize", "Page size không hợp lệ");
        }

        if (binding.hasErrors()) {
            return "settings";
        }

        settingService.save(form);

        model.addAttribute("settings", form);
        model.addAttribute("message", "Cập nhật thành công");

        return "result";
    }

    @GetMapping
    public String show(Model model, @RequestParam(value = "saved", required = false) String saved) {
        model.addAttribute("settings", settingService.find());
        if (saved != null) model.addAttribute("message", "Cập nhật thành công");
        return "settings";
    }
}
