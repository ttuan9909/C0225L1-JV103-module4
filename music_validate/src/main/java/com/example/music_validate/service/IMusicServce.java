package com.example.music_validate.service;

import com.example.music_validate.entity.Music;

import java.util.List;

public interface IMusicServce {
    List<Music> findAll();
    boolean save(Music music);
}
