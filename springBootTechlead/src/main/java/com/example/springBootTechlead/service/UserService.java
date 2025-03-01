package com.example.springBootTechlead.service;


import com.example.springBootTechlead.model.dto.LoginDto;
import com.example.springBootTechlead.model.entity.User;
import com.example.springBootTechlead.repository.RoleRepository;
import com.example.springBootTechlead.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, RoleRepository roleRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    public ResponseEntity<Object> login(LoginDto loginDto, BindingResult result){
        if(result.hasErrors()){
            var errorList = result.getAllErrors();
            var errorMap = new HashMap<String, String>();
            for (org.springframework.validation.ObjectError objectError : errorList) {
                var error = (FieldError) objectError;
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMap);
        }
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user == null) {
            return ResponseEntity.status(401).body("Wrong username");
        }
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        if (!bCryptEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Wrong password");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),loginDto.getPassword()
                )
        );
        String jwtToken = jwtService.generateToken(user);
        var response = new HashMap<String, Object>();
        response.put("token", jwtToken);
        response.put("user", user.getUsername());
        return ResponseEntity.ok(response);

    }

    public ResponseEntity<Object> register(LoginDto userDto, BindingResult result, String role){
        if(result.hasErrors()){
            var errorList = result.getAllErrors();
            var errorMap = new HashMap<String, String>();
            for (org.springframework.validation.ObjectError objectError : errorList) {
                var error = (FieldError) objectError;
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMap);
        }

        if(userDto.getUsername().contains(" ") || userDto.getPassword().contains(" ")){
            var errorMap = new HashMap<String, String>();
            errorMap.put("Error","Username/Password is not valid!");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("error", "Username or Password must not contain spaces."));
        }

        try{
            BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(bCryptEncoder.encode(userDto.getPassword()));
            if(roleRepository.findRoleByName(role) == null){
                return ResponseEntity.status(401).body("Role not found");
            }

            user.setRole(roleRepository.findRoleByName(role));
            // System.out.println(roleRepository.findRoleByName(role));
            //user.setRole(roleRepository.findRoleById(1));

            User userCheck = userRepository.findByUsername(userDto.getUsername());
            if(userCheck != null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Username is already in use."));
            }
            userRepository.save(user);

            String jwtToken = jwtService.generateToken(user);

            var response = new HashMap<String, Object>();
            response.put("token", jwtToken);
            response.put("user", user.getUsername());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Xử lý lỗi bất ngờ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred."));
        }

    }

}