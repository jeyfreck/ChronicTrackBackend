package com.chronicktrack.chronictrack.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.dto.AuthRequest;
import com.chronicktrack.chronictrack.dto.AuthResponse;
import com.chronicktrack.chronictrack.entity.Role;
import com.chronicktrack.chronictrack.entity.User;
import com.chronicktrack.chronictrack.service.UserService;
import com.chronicktrack.chronictrack.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

private final AuthenticationManager authenticationManager;
private final JwtUtil jwtUtil;
private final UserService userService;

public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil; 
    this.userService = userService;
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request){
Authentication authentication = authenticationManager.authenticate(new 
UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

String token = jwtUtil.generateToken(request.getEmail());
return ResponseEntity.ok(new AuthResponse(token));
}

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody AuthRequest request){
try{
  User user = new User();
   user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER); 

        User registeredUser = userService.register(user);
     
      
        return ResponseEntity.ok(registeredUser);
} catch(Exception e){
return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
}
}
}
