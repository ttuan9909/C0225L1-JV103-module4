package com.example.demo_validate.service;

import com.example.demo_validate.dto.UserDto;
import com.example.demo_validate.entity.User;
import com.example.demo_validate.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto save(UserDto userDto) {
        return userRepository.save(userDto);
    }
}
