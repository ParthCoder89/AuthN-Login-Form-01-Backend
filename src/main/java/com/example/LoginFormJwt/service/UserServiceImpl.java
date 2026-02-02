package com.example.LoginFormJwt.service;

import com.example.LoginFormJwt.entity.User;
import com.example.LoginFormJwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
            return userRepository.findByUsername(username).orElse(null);
        }
}


