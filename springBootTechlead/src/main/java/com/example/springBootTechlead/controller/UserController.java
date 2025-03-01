package com.example.springBootTechlead.controller;


import com.example.springBootTechlead.model.dto.AccountDto;
import com.example.springBootTechlead.model.dto.LoginDto;
import com.example.springBootTechlead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Validated @RequestBody LoginDto user, BindingResult result){
        return userService.register(user, result, "USER");
    }

    @PostMapping("/register-by-admin")
    public ResponseEntity<Object> registerByAdmin(@Validated @RequestBody AccountDto accountDto, BindingResult result){
        LoginDto user = new LoginDto(accountDto.getUsername(), accountDto.getPassword());
        return userService.register(user, result, accountDto.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginDto user, BindingResult result){
        return userService.login(user,result);
    }

}