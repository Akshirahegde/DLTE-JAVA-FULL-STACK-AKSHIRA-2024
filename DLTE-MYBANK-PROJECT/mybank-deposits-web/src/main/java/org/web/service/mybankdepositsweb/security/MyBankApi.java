package org.web.service.mybankdepositsweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class MyBankApi {
    @Autowired
MyBankServices services;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyBankCustomer save(@RequestBody MyBankCustomer myBankCustomers){
        myBankCustomers.setPassword(passwordEncoder.encode(myBankCustomers.getPassword()));
        return services.signUp(myBankCustomers);
    }
}
