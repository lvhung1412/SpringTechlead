package com.example.springBootTechlead.controller;


import com.example.springBootTechlead.model.dto.LoginDto;
import com.example.springBootTechlead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Validated @RequestBody LoginDto user, BindingResult result){
        return userService.register(user, result);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginDto user, BindingResult result){
        return userService.login(user,result);
    }

}