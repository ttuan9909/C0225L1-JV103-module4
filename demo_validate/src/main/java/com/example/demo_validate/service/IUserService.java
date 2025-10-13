package com.example.demo_validate.service;

import com.example.demo_validate.dto.UserDto;
import com.example.demo_validate.entity.User;

import java.util.List;

public interface IUserService {
    List<UserDto> findAll();
    UserDto save(UserDto userDto);
}
