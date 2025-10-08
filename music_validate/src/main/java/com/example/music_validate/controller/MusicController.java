package com.example.music_validate.controller;

import com.example.music_validate.entity.Music;
import com.example.music_validate.service.MusicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/musics")
public class MusicController {

    private final MusicService musicService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("musics", musicService.findAll());
        return "musics/list";                
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("music", new Music());
        return "musics/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("music") Music music,
                       BindingResult bindingResult,
                       RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            return "musics/form";
        }

        musicService.save(music);
        ra.addFlashAttribute("mess", "Thêm/cập nhật thành công");
        return "redirect:/musics";
    }
}
