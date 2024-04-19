package com.example.jrproject.web.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtTokenFilter {

    private final JwtTokenProvider jwtTokenProvider;

}
