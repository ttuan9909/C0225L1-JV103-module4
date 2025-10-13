package com.example.demo_validate.repository;

import com.example.demo_validate.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserDto, Long> {
}
