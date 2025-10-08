package com.example.demo_validate.service;

import com.example.demo_validate.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User save(User user);
}
