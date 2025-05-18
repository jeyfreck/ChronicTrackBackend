package com.chronicktrack.chronictrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.User;


public interface  UserRepository extends JpaRepository<User, Long>{
Optional<User> findByEmail(String email);
boolean existByEmail(String email);
boolean existByUsername(String username);
Optional<User> findByUsername(String username);
}
