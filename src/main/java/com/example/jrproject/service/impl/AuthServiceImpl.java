package com.example.jrproject.service.impl;

import com.example.jrproject.domain.user.User;
import com.example.jrproject.service.AuthService;
import com.example.jrproject.service.UserService;
import com.example.jrproject.web.dto.auth.JwtRequest;
import com.example.jrproject.web.dto.auth.JwtResponse;
import com.example.jrproject.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            User user = userService.getByUsername(loginRequest.getUsername());
            jwtResponse.setId(user.getId());
            jwtResponse.setUsername(user.getUsername());
            jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(),user.getUsername(),user.getRoles()));
            jwtResponse.setAccessToken(jwtTokenProvider.createRefreshToken(user.getId(),user.getUsername()));
            return jwtResponse;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error in AuthServiceImpl class.");
        }
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
