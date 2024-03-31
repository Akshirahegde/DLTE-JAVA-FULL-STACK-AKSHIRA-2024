package com.security.taskrestsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MyBankusersApi {
    @Autowired
    MyBankUsersService myBankUsersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyBankUsers save(@RequestBody MyBankUsers myBankUsers) {
        myBankUsers.setPassword(passwordEncoder.encode(myBankUsers.getPassword()));
        return myBankUsersService.signup(myBankUsers);
    }
}