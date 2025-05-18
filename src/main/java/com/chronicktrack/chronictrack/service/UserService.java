package com.chronicktrack.chronictrack.service;

import com.chronicktrack.chronictrack.entity.User;

public interface UserService {
    
User register (User  user);
User findByEmail(String email);
}
