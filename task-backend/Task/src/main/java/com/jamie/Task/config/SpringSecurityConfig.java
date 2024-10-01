package com.jamie.Task.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
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
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests.requestMatchers("/api/auth/**").permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));  // Allow requests from frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Allow all methods
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));  // Allow relevant headers
        configuration.setAllowCredentials(true);  // Allow credentials (cookies, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Apply this CORS config to all endpoints
        return source;
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


