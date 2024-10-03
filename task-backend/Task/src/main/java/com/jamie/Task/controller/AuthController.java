package com.jamie.Task.controller;

import com.jamie.Task.dto.JwtAuthResponseDto;
import com.jamie.Task.dto.LoginDto;
import com.jamie.Task.dto.RegisterDto;
import com.jamie.Task.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private AuthService authService;

    // Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto) {

        JwtAuthResponseDto jwtAuthResponseDto = authService.login(loginDto);

        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.OK);
    }

}
