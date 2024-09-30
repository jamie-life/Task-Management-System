package com.jamie.Task.service.Implementation;

import com.jamie.Task.dto.LoginDto;
import com.jamie.Task.dto.RegisterDto;
import com.jamie.Task.entity.Role;
import com.jamie.Task.entity.User;
import com.jamie.Task.exception.TaskAPIException;
import com.jamie.Task.repository.RoleRepository;
import com.jamie.Task.repository.UserRepository;
import com.jamie.Task.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDto registerDto) {

        // Check Username & Email Exist
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new TaskAPIException(HttpStatus.BAD_REQUEST, "Username is already in use");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new TaskAPIException(HttpStatus.BAD_REQUEST, "Email is already in use");
        }

        User newUser = new User();
        newUser.setName(registerDto.getName());
        newUser.setUsername(registerDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newUser.setEmail(registerDto.getEmail());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        newUser.setRoles(roles);

        userRepository.save(newUser);

        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Logged in Successfully!";
    }
}
