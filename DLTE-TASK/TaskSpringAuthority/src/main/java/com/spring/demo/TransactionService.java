package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    //create method
    public TransactionsModel apiSave(TransactionsModel transactionsModel){
//        int ack = jdbcTemplate.update("insert into transactions_model values(?,?,?,?,?,?)",new Object[]{
//                transactionsModel.getTransactionId(),
//                transactionsModel.getTransactionDate(),
//                transactionsModel.getTransactionBy(),
//                transactionsModel.getTransactionTo(),
//                transactionsModel.getTransactionAmount(),
//                transactionsModel.getTransactionFor()
//        });
//        if(ack!=0)
//            return transactionsModel;
//        else
//            throw  new TransactionException("Insertion Failed");
        jdbcTemplate.update("insert into transactions_model values(?,?,?,?,?,?)",new Object[]{
                transactionsModel.getTransactionId(),
                transactionsModel.getTransactionDate(),
                transactionsModel.getTransactionBy(),
                transactionsModel.getTransactionTo(),
                transactionsModel.getTransactionAmount(),
                transactionsModel.getTransactionFor()
        });
        return transactionsModel;
    }

    //findBY method
    public List<TransactionsModel> apiFindBySender(String sender){
        return jdbcTemplate.query("select * from transactions_model where transaction_by=?",new Object[]{sender},new BeanPropertyRowMapper<>(TransactionsModel.class
        ));

    }

    //FindTo method
    public List<TransactionsModel> apiFindByReceiver(String receiver){
        return jdbcTemplate.query("select * from transactions_model where transaction_to=?",new Object[]{receiver},new BeanPropertyRowMapper<>(TransactionsModel.class
        ));

    }

    //FindAmount
    public List<TransactionsModel> apiFindByAmount(Long amount){
        return jdbcTemplate.query("select * from transactions_model where transaction_amount=?",new Object[]{amount},new BeanPropertyRowMapper<>(TransactionsModel.class
        ));




}
