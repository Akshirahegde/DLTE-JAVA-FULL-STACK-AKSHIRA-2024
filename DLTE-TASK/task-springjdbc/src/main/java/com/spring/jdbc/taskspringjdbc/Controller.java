package com.spring.jdbc.taskspringjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class Controller {
    @Autowired
    TransactionService transactionService;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("/new")
    public Transaction saved(@RequestBody Transaction transaction) {
        Transaction transaction1=null;
        try {
            transaction1 = transactionService.apiSave(transaction);
        } catch (TransactionException transactionException) {
            logger.warn(transactionException.toString());
        }
        return transaction1;
    }
    @GetMapping("/one/{number}")
    public Optional<Transaction> gettingOne(@PathVariable("number") Integer number){
        return transactionService.apiFindById(number);
    }

    @GetMapping("/sender/{transactionFrom}")
    public List<Transaction> gettingSender(@PathVariable("transactionFrom") String transactionFrom) {
        return transactionService.apiFindBySender(transactionFrom);
    }

    @GetMapping("/receiver/{receiver}")
    public List<Transaction> gettingReceiver(@PathVariable("receiver") String receiver) {
        return transactionService.apiFindByReciever(receiver);
    }

    @GetMapping("/view/{transactionamount}")
    public List<Transaction> gettingAmount(@PathVariable("transactionamount") Integer transactionamount) {
        return transactionService.apiFindByAmount(transactionamount);
    }
}
