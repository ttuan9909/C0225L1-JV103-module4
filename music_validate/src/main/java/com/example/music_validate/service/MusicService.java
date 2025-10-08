package com.example.music_validate.service;

import com.example.music_validate.entity.Music;
import com.example.music_validate.repository.IMusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService implements IMusicServce{
    private final IMusicRepository repository;

    public MusicService(IMusicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Music> findAll() {
        return repository.findAll();
    }

    @Override
    public Music save(Music music) {
        return repository.save(music);
    }
}
