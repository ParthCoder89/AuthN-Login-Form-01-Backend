package com.example.LoginFormJwt.Controller;

import com.example.LoginFormJwt.Dto.UserDto;
import com.example.LoginFormJwt.entity.User;
import com.example.LoginFormJwt.security.JwtUtil;
import com.example.LoginFormJwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userService.saveUser(user);
        return "Signup Successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user == null) throw new RuntimeException("User not found");
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid password");

        return jwtUtil.generateToken(user.getUsername());
    }
}
