package com.spring.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersApi {
    @Autowired
    UsersService myBankUserServices;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Users save(@RequestBody Users myBankUsers){
        myBankUsers.setPassword(passwordEncoder.encode(myBankUsers.getPassword()));
        return myBankUserServices.signingUp(myBankUsers);
    }
}
