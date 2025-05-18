package com.chronicktrack.chronictrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chronicktrack.chronictrack.service.CustomUserDetailsService;
import com.chronicktrack.chronictrack.util.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

private final CustomUserDetailsService userDetailsService;
private final JwtFilter jwtFilter;

public SecurityConfig(CustomUserDetailsService userDetailsService, 
JwtFilter jwtFilter){
this.userDetailsService = userDetailsService;
this.jwtFilter = jwtFilter;
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/auth/**").permitAll()
        .anyRequest().authenticated()
    )
    .sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    )
    .authenticationProvider(authenticationProvider())
    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

return http.build();
}

@Bean
public AuthenticationProvider authenticationProvider(){
DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
provider.setUserDetailsService(userDetailsService);
provider.setPasswordEncoder(passwordEncoder());
return provider;
}

@Bean
public PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration 
config) throws Exception{
return config.getAuthenticationManager();
}
}
