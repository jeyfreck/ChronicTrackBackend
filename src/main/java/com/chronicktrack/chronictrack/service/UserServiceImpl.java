package com.chronicktrack.chronictrack.service;

import java.util.Collections;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.User;
import com.chronicktrack.chronictrack.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
private final UserRepository userRepository;
private final BCryptPasswordEncoder passwordEncoder;

public UserServiceImpl(UserRepository userRepository){
this.userRepository = userRepository;
this.passwordEncoder = new BCryptPasswordEncoder(); 
}

@Override
public User register(User user) {
if(userRepository.existByEmail(user.getEmail())){
throw new IllegalArgumentException("Email already in use");
}

if(userRepository.existByUsername(user.getUsername())){
throw new IllegalArgumentException("Username already in use");
}
user.setPassword(passwordEncoder.encode(user.getPassword()));
user.setRoles(Collections.singleton("PATIENT"));

return userRepository.save(user);
}

@Override
public User findByEmail(String email){
    return userRepository.findByEmail(email)
    .orElseThrow(() -> new IllegalArgumentException("User not found"));
   }

}
