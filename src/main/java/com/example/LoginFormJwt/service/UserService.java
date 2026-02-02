package com.example.LoginFormJwt.service;

import com.example.LoginFormJwt.entity.User;

public interface UserService {
    User saveUser(User user);
    User findByUsername(String username);
}

