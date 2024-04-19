package com.example.jrproject.web.controller;

import com.example.jrproject.domain.user.User;
import com.example.jrproject.service.AuthService;
import com.example.jrproject.service.UserService;
import com.example.jrproject.web.dto.auth.JwtRequest;
import com.example.jrproject.web.dto.auth.JwtResponse;
import com.example.jrproject.web.dto.user.UserDto;
import com.example.jrproject.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto){
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }

}
