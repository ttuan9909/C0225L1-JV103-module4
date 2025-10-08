package com.example.demo_validate.repository;

import com.example.demo_validate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
