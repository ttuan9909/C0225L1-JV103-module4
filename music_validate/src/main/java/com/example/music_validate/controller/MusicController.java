package com.example.music_validate.controller;

import com.example.music_validate.dto.MusicDto;
import com.example.music_validate.entity.Music;
import com.example.music_validate.service.MusicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        model.addAttribute("musicDto", new MusicDto());
        return "musics/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("musicDto") MusicDto musicDto,
                       BindingResult bindingResult,
                       RedirectAttributes ra,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "musics/form";
        }

        Music music = new Music();
        BeanUtils.copyProperties(musicDto, music);
        musicService.save(music);
        ra.addFlashAttribute("mess", "Thêm/cập nhật thành công");
        return "redirect:/musics";
    }
}
