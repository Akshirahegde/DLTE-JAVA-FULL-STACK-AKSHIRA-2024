package com.jdbc.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction apiInsert(Transaction transaction) {
        int check = jdbcTemplate.update("insert into TRANSACTIONS values (?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionBy(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionAmount(),
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

        if (check != 0)
            return transaction;
        else
            return null;

    }

    public List<Transaction> apiByReciever(String reciever) {
        return jdbcTemplate.query("select * from TRANSACTIONS where TRANSACTION_TO=?",
                new Object[]{reciever},
               // new TransactionMapper()
                new BeanPropertyRowMapper<>(Transaction.class));
    }

    public List<Transaction> apiByAmount(long minAmount, Long maxAmount) {
        List<Transaction> transactionList = jdbcTemplate.query("select * from TRANSACTIONS where TRANSACTION_AMOUNT between ? and ?",
                new Object[]{minAmount, maxAmount},
               // new TransactionMapper()
        new BeanPropertyRowMapper<>(Transaction.class));
        return transactionList;

    }


    public List<Transaction> apiBySender(String senderName) {
        return jdbcTemplate.query("select * from TRANSACTIONS where TRANSACTION_BY=?",
                new Object[]{senderName},
               // new TransactionMapper()
        new BeanPropertyRowMapper<>(Transaction.class));
        //  new BeanPropertyRowMapper<>(Transaction.class));
    }


    public Transaction updateTransaction(Transaction transaction) {
        int ack = jdbcTemplate.update("update TRANSACTIONS set TRANSACTION_BY=? ,TRANSACTION_TO=?,TRANSACTION_AMOUNT=? where TRANSACTION_ID=?",
                new Object[]{transaction.getTransactionBy(), transaction.getTransactionTo(), transaction.getTransactionAmount(), transaction.getTransactionId()});
        if (ack != 0)
            return transaction;
        else
            return null;
    }

    public String deleteTransaction(Date startDate, Date endDate) {
        int acknowledge = jdbcTemplate.update("delete from TRANSACTIONS where TRANSACTION_DATE between ? and ?",
                new Object[]{startDate, endDate});
        if (acknowledge != 0)
            return "Transaction details deleted";
        else
            return "No transactions to delete";
    }

    public class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getLong("transactionId"));
            transaction.setTransactionDate(rs.getDate("transactionDate"));
            transaction.setTransactionTo(rs.getString("transactionBy"));
            transaction.setTransactionAmount(rs.getLong("transactionTo"));
            transaction.setTransactionFor(rs.getString("transactionAmount"));
            transaction.setTransactionBy(rs.getString("transactionFor"));
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





