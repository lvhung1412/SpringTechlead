package com.example.springBootTechlead.controller;


import com.example.springBootTechlead.model.dto.LoginDto;
import com.example.springBootTechlead.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Validated @RequestBody LoginDto userDto, BindingResult result){
        return accountService.register(userDto, result);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginDto loginDto, BindingResult result){
        return accountService.login(loginDto,result);
    }

}