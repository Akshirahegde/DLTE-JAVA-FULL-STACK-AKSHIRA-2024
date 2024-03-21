package com.jpa.taskjpa.controller;

import com.jpa.taskjpa.model.Account;
import com.jpa.taskjpa.remotes.AccountRepository;
import com.jpa.taskjpa.services.Accountservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestApi {
    @Autowired
    Accountservices accountServices;
    AccountRepository accountRepository;

    @PostMapping("/open")
    public Account openAccount(@RequestBody Account account) {
        return accountServices.callSave(account);
    }

    @PutMapping("/update")
    public Account updateAccount( @RequestBody Account account) {
        //Account existingAccount = accountRepository.findById(id).orElse(null);
        return accountServices.callSave(account);
    }

    @GetMapping("")
    public List<Account> getAllAccounts() {
        return accountServices.callFindAll();
    }
}
