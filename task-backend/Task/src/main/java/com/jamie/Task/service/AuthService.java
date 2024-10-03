package com.jamie.Task.service;

import com.jamie.Task.dto.JwtAuthResponseDto;
import com.jamie.Task.dto.LoginDto;
import com.jamie.Task.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    JwtAuthResponseDto login(LoginDto loginDto);
}
