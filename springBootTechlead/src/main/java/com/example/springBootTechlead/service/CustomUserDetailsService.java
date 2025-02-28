package com.example.springBootTechlead.service;

import com.example.springBootTechlead.model.entity.Role;
import com.example.springBootTechlead.repository.RoleRepository;
import com.example.springBootTechlead.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.springBootTechlead.model.entity.User appUser = userRepository.findByUsername(username);
        Role userRole = roleRepository.findRoleById(appUser.getRole().getId());
        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(userRole.getName()).build();
    }
}