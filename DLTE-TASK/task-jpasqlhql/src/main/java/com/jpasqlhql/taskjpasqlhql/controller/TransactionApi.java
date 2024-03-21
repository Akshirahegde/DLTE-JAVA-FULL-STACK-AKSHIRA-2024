package com.jpasqlhql.taskjpasqlhql.controller;

import com.jpasqlhql.taskjpasqlhql.model.TransactionDetails;
import com.jpasqlhql.taskjpasqlhql.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transactions")
public class TransactionApi {
    @Autowired
    TransactionService transactionService;
    @PostMapping(value = "/create",consumes = "application/xml",produces = "application/xml")
    public TransactionDetails callNewTransaction(@RequestBody TransactionDetails transactionDetails){
        return transactionService.newTransactions(transactionDetails);
    }

    @GetMapping("/find/{name}/{type}")
    public List<TransactionDetails> callFindTransaction(@PathVariable("name") String name,@PathVariable("type") String type){
        return transactionService.findAllByUser(name,type);
    }
    @GetMapping("/{transactionAmount1}/{transactionAmount2}")
    public List<TransactionDetails> callFindByRange(@PathVariable("transactionAmount1") Long traansactionAmount1,@PathVariable("transactionAmount2") Long transacionAmount2){
        return transactionService.findByTransactionRange(traansactionAmount1,transacionAmount2);
    }
}
