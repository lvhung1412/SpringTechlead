package com.example.springBootTechlead.service;


import com.example.springBootTechlead.model.dto.LoginDto;
import com.example.springBootTechlead.model.entity.User;
import com.example.springBootTechlead.repository.RoleRepository;
import com.example.springBootTechlead.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.regex.Pattern;

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


    public ResponseEntity<Object> profile(Authentication auth){
        var response = new HashMap<String, Object>();
        response.put("Username",auth.getName());
        response.put("Authentication",auth.getAuthorities());

        var user = userRepository.findByUsername(auth.getName());
        response.put("User",user);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> login(LoginDto loginDto, BindingResult result){
        if(result.hasErrors()){
            var errorList = result.getAllErrors();
            var errorMap = new HashMap<String, String>();
            for(int i = 0;i < errorList.size();i++){
                var error = (FieldError) errorList.get(i);
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
        }

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),loginDto.getPassword()
                    )
            );

            User user = userRepository.findByUsername(loginDto.getUsername());
            if(user == null){
                return ResponseEntity.badRequest().body("Username isn't exist!");
            }
            String jwtToken = jwtService.generateToken(user);
            var response = new HashMap<String, Object>();
            response.put("token", jwtToken);
            response.put("user", user.getUsername());
            return ResponseEntity.ok(response);

        } catch (Exception e){
            System.out.println("There is an exception : ");
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body("Wrong username or password!");
    }

    private boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public ResponseEntity<Object> register(LoginDto userDto, BindingResult result){

        if(result.hasErrors()){
            var errorList = result.getAllErrors();
            var errorMap = new HashMap<String, String>();
            for(int i = 0;i < errorList.size();i++){
                var error = (FieldError) errorList.get(i);
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
        }

        if(userDto.getUsername().contains(" ") || userDto.getPassword().contains(" ")){
            var errorMap = new HashMap<String, String>();
            errorMap.put("Error","Username/Password is not valid!");
            return ResponseEntity.badRequest().body(errorMap);
        }


        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(bCryptEncoder.encode(userDto.getPassword()));
        user.setRole(roleRepository.findRoleById(1));

        User userCheck = userRepository.findByUsername(userDto.getUsername());
        if(userCheck != null){
            return ResponseEntity.badRequest().body("Username already used!");
        }
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        var response = new HashMap<String, Object>();
        response.put("token", jwtToken);
        response.put("user", user.getUsername());

        return ResponseEntity.ok(response);
    }

}