package com.migratejdbc.taskmigratejdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/jdbctransaction")
    public class MyController {
        @Autowired
        MyService myService;
        //for new transaction
        @PostMapping("/new")
        public Transaction saved(@RequestBody Transaction transaction){
            Transaction transactions1=null;
            try{
                transactions1=myService.apiInsert(transaction);

            }catch (Exception exp){
                System.out.println(exp);
            }
            return transactions1;

        }
        //for getting all transactions by senders name
        @GetMapping("/sender/{senderName}")
        public List<Transaction> getSender(@PathVariable String senderName){
            List<Transaction> transactionName=null;
            try{
                transactionName=myService.apiBySender(senderName);
            }catch (TransactionException exp){
                System.out.println(exp);
            }
            return transactionName;
        }
        //for getting all transaction by recevers name
        @GetMapping("/recieve/{receiverName}")
        public List<Transaction> getReciever(@PathVariable String receiverName){
            List<Transaction> transactionName=null;
            try{
                transactionName=myService.apiByRecever(receiverName);
            }catch (TransactionException exp){
                System.out.println(exp);
            }
            return transactionName;
        }
        //for getting all transaction by amount
        @GetMapping("/amount/{amount}")
        public List<Transaction> getAmount(@PathVariable Long amount){
            List<Transaction> transactionName=null;
            try{
                transactionName=myService.apiByAmount(amount);
            }catch (TransactionException exp){
                System.out.println(exp);
            }
            return transactionName;
        }



    }

