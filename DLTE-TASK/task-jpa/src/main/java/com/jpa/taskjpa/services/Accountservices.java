package com.jpa.taskjpa.services;

import com.jpa.taskjpa.model.Account;
import com.jpa.taskjpa.remotes.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Accountservices {
    @Autowired
    AccountRepository accountRepository;
    public Account callSave(Account account){
        return accountRepository.save(account);
    }
    public List<Account> callFindAll(){
        return (List<Account>) accountRepository.findAll();
    }

}