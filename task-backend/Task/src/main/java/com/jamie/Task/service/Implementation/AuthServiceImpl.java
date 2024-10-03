package com.jamie.Task.service.Implementation;

import com.jamie.Task.dto.JwtAuthResponseDto;
import com.jamie.Task.dto.LoginDto;
import com.jamie.Task.dto.RegisterDto;
import com.jamie.Task.entity.Role;
import com.jamie.Task.entity.User;
import com.jamie.Task.exception.TaskAPIException;
import com.jamie.Task.repository.RoleRepository;
import com.jamie.Task.repository.UserRepository;
import com.jamie.Task.security.JwtTokenProvider;
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
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

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
    public JwtAuthResponseDto login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
                loginDto.getUsernameOrEmail());


        String username = "";
        String userRole = "";
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            username = user.getUsername();
            Optional<Role> optionalRole = user.getRoles().stream().findFirst();

            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                userRole = role.getName();
            }
        }

        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setUsername(username);
        jwtAuthResponseDto.setRole(userRole);
        jwtAuthResponseDto.setAccessToken(token);



        return jwtAuthResponseDto;
    }
}
