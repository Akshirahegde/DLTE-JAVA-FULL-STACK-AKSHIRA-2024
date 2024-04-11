package com.spring.jdbc.taskspringjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {
  @Autowired
    private JdbcTemplate jdbcTemplate;


    public Transaction apiSave(Transaction transaction){
        int ack = jdbcTemplate.update("insert into transaction_jdbc values(?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionFrom(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionRemarks()
                });
        if(ack!=0)
            return transaction;
        else
            throw new TransactionException(" insertion failed");
    }

    public Optional<Transaction> apiFindById(Integer transactionId){
        return Optional.of(jdbcTemplate.queryForObject("select * from transaction_jdbc where transactionid=?",
                new Object[]{transactionId},
                new BeanPropertyRowMapper<>(Transaction.class)
        ));
    }

    public List<Transaction> apiFindBySender(String sender) {
        return jdbcTemplate.query("select * from transaction_jdbc where transactionfrom=?",
                new Object[]{sender},
               new TransactionMapper());
                //new BeanPropertyRowMapper<>(Transaction.class));
    }

    public List<Transaction> apiFindByReciever(String receiver) {
        return jdbcTemplate.query("select * from transaction_jdbc where transactionto=?",
                new Object[]{receiver},
              new BeanPropertyRowMapper<>(Transaction.class));
    }

    public List<Transaction> apiFindByAmount(Integer transactionamount) {
        return jdbcTemplate.query("select * from transaction_jdbc where transactionamount=?",
                new Object[]{transactionamount},
                new BeanPropertyRowMapper<>(Transaction.class));
    }
//
//    {"transactionId":90,
//            "transactionTo":"Rampa",
//            "transactionFrom":"Sampa",
//            "transactionAmount":5000,
//            "transactionRemarks":"Family"
//    }
//    public List<Transaction> apiFindByRemarks(String remark) {
//        return jdbcTemplate.query("select * from transaction where transactionRemarks=?",
//                new Object[]{remark},
//                new TransactionMapper());
//    }

    public class TransactionMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction =new Transaction();
            transaction.setTransactionId(rs.getInt("transactionid"));
                transaction.setTransactionTo(rs.getString("transactionto"));

            transaction.setTransactionFrom(rs.getString("transactionfrom"));
            transaction.setTransactionAmount(rs.getInt("transactionamount"));
            transaction.setTransactionRemarks(rs.getString("transactionremarks"));
            return transaction;
        }
    }

}
