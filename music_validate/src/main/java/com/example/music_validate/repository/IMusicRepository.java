package com.example.music_validate.repository;

import com.example.music_validate.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMusicRepository extends JpaRepository<Music, Integer> {
}
