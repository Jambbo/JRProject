package com.example.jrproject.service;

import com.example.jrproject.web.dto.auth.JwtRequest;
import com.example.jrproject.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}
