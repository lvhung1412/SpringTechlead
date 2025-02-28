package com.example.springBootTechlead.service;

import com.example.springBootTechlead.model.dto.LoginDto;
import com.example.springBootTechlead.model.entity.User;
import com.example.springBootTechlead.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(LoginDto input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public User authenticate(LoginDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword())
        );
        return userRepository.findByUsername(input.getUsername());
    }

    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}