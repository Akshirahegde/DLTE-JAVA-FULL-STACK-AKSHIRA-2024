package com.migratejdbc.taskmigratejdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction apiInsert(Transaction transaction){
        int check=jdbcTemplate.update("insert into TRANSACTIONS values (?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionDate(),
                        transaction.getTransactionBy(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionFor()

                });
//        {
//            "transactionId": 11,
//                "transactionDate":"2024-03-12",
//                "transactionBy": "Ramesh",
//                "transactionTo":"Suresh",
//                "transactionAmount":5000,
//                "transactionFor":"Family"
//        }

        if(check!=0)
            return transaction;
        else
            return null;

    }
    //query for all transaction by reciever name
    public List<Transaction> apiByReciever(String reciever){
        return jdbcTemplate.query("select * from TRANSACTIONS where TRANSACTION_TO=?",
                new Object[]{reciever},
                new TransactionMapper());
    }
    //query for all transaction by amount
    public List<Transaction> apiByAmount(Long amount){
        return jdbcTemplate.query("select * from TRANSACTIONS where TRANSACTION_AMOUNT=?",
                new Object[]{amount},
                new TransactionMapper());
    }


    //query for all transaction by sender name
    public List<Transaction> apiBySender(String senderName){
        return jdbcTemplate.query("select * from TRANSACTIONS where TRANSACTION_BY=?",
                new Object[]{senderName},
               new TransactionMapper());
              //  new BeanPropertyRowMapper<>(Transaction.class));
    }

    //for multiple object retrun in sender ,reciver and amount
    private class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction=new Transaction();
            transaction.setTransactionId(rs.getLong(1));
            transaction.setTransactionDate(rs.getDate(2));
            transaction.setTransactionTo(rs.getString(3));
            transaction.setTransactionAmount(rs.getLong(4));
            transaction.setTransactionFor(rs.getString(5));
            transaction.setTransactionBy(rs.getString(6));
            return transaction;
        }
    }

}
//private class TransactionMapper implements RowMapper<Transactions>{
//    @Override
//     public Transactions mapRow(R)
//    Transactions transactions=new Transactions();
//
//}