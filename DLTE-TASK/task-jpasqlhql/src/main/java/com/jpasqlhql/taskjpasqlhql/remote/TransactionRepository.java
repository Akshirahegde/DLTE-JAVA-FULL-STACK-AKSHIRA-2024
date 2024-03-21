package com.jpasqlhql.taskjpasqlhql.remote;


import com.jpasqlhql.taskjpasqlhql.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionDetails,Integer> {
//sql query use table name
    @Query(value="select * from Transaction_Details where transaction_to=:user and transaction_type=:type",nativeQuery=true)
   List<TransactionDetails> findAllByUserAndType(String user,String type);
    //hql query use Entity name
    @Query("from TransactionDetails where transactionAmount between :transactionAmount1 and :transactionAmount2")
    List<TransactionDetails> findAllByRangeTransactionAmount(Long transactionAmount1, Long transactionAmount2);


}
