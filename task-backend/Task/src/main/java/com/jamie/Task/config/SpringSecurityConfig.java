package com.jamie.Task.config;

import com.jamie.Task.security.JwtAuthenticationEntryPoint;
import com.jamie.Task.security.JwtAuthenticationFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@AllArgsConstructor
@Configuration
//@EnableWebSecurity
@EnableMethodSecurity()
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*Method Level Security*/
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for testing
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/api/auth/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        http.exceptionHandling(exception ->
                exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://master.d281cg07p0wk8v.amplifyapp.com")); //allows React to access the API from origin on port 3000. Change accordingly
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//
//    // Using Memory Authentication
//    /*@Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails Jamie = User.builder()
//                .username("Jamie")
//                .password(passwordEncoder().encode("Test"))
//                .roles("USER").build();
//
//        UserDetails Admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN").build();
//
//
//        return new InMemoryUserDetailsManager(Jamie, Admin);
//    }*

//    // Role Based Authorization By HTTP Request
//    /*@Bean
//    SecurityFilterChain methodSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorizeRequests) -> {
//                    authorizeRequests.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "User");
//                    authorizeRequests.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "User");
//
//                    authorizeRequests.requestMatchers(HttpMethod.GET,  "/api/**").permitAll(); *//* If we want to make the GET Related HTTP
//                    request public and without the need of credentials*//*
//                    authorizeRequests.anyRequest().authenticated();
//                }).httpBasic(Customizer.withDefaults());
//        return http.build();
//    }*/

}


