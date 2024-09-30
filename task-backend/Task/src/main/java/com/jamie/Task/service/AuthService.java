package com.jamie.Task.service;

import com.jamie.Task.dto.LoginDto;
import com.jamie.Task.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
