package com.jpasqlhql.taskjpasqlhql.services;

import com.jpasqlhql.taskjpasqlhql.model.TransactionDetails;
import com.jpasqlhql.taskjpasqlhql.remote.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    public TransactionDetails newTransactions(TransactionDetails transactionDetails){
        return transactionRepository.save(transactionDetails);
    }
    public List<TransactionDetails> findAllByUser(String user, String type){
        return transactionRepository.findAllByUserAndType(user, type);
    }
    public List<TransactionDetails> findByTransactionRange(Long transactionAmount1,Long transactionAmount2){
        return transactionRepository.findAllByRangeTransactionAmount(transactionAmount1,transactionAmount2);
    }
}
