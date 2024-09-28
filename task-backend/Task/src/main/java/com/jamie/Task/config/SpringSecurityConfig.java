package com.jamie.Task.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Role Based Authorization By HTTP Request
    /*@Bean
    SecurityFilterChain methodSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
                    authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
                    authorizeRequests.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "User");
                    authorizeRequests.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "User");

                    authorizeRequests.requestMatchers(HttpMethod.GET,  "/api/**").permitAll(); *//* If we want to make the GET Related HTTP
                    request public and without the need of credentials*//*
                    authorizeRequests.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }*/

    /*Method Level Security*/
    @Bean
    SecurityFilterChain methodSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    // Not using Memory Authentication
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails Jamie = User.builder()
                .username("Jamie")
                .password(passwordEncoder().encode("Test"))
                .roles("USER").build();

        UserDetails Admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();


        return new InMemoryUserDetailsManager(Jamie, Admin);
    }*/
}


