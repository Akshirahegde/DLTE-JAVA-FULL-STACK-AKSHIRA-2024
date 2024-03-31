package com.jdbc.soap.taskjdbcsoap;

import com.jdbc.soap.Transaction;
import com.jdbc.soap.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TaskjdbcsoapApplicationTests {
        @Mock
        private JdbcTemplate jdbcTemplate;

@InjectMocks
        private TransactionService transactionService;

        private List<Transaction> initalTransactions(){
            List<Transaction> newList=new ArrayList<>();
            Transaction transaction1=new Transaction(17655L,new Date(2024/03/4),"Siya","Ram",9000L,"Friend");
            Transaction transaction2=new Transaction(12345L,new Date(2024/9/8),"Siya","Ram",12000L,"Friend");
            newList.add(transaction1);
            newList.add(transaction2);
            return newList;
        }

        @Test
        void testGetByAmount(){
            Transaction transaction1=new Transaction(17655L,new Date(2024/03/4),"Siya","Ram",9000L,"Friend");
            Transaction transaction2=new Transaction(12345L,new Date(2024/9/8),"Siya","Ram",12000L,"Friend");

            List<Transaction> transactionsList= Stream.of(transaction1,transaction2).collect(Collectors.toList());
            when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionsList);

            List<Transaction> actualOne=transactionService.apiByAmount(50000,6000L);
            System.out.println(actualOne);
            assertEquals(transactionsList,actualOne);



        }
        //fail
        @Test
        void testUpdateTransaction(){
            List<Transaction> test=initalTransactions();
            when(jdbcTemplate.query(any(String.class), any(Object[].class), any(TransactionService.TransactionMapper.class))).thenReturn(test);
            Transaction result=transactionService.updateTransaction(test.get(0));
            assertEquals(test.get(1).toString(),result.toString());

        }


        @Test
        void testBySender(){
            Transaction transaction1=new Transaction(1555484L,new Date(2024/9/6),"Siya","Ram",5000L,"Emergency");
            Transaction transaction2=new Transaction(98746L,new Date(2023/7/6),"Shiva","Parvathi",6000L,"Emergency");

            List<Transaction> transactionsList= Stream.of(transaction1,transaction2).collect(Collectors.toList());
            when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionsList);

            List<Transaction> result=transactionService.apiBySender("Ramesh");
            assertNotNull(result);
            assertEquals(transactionsList,result);
        }
        @Test
        void testRemoveTransactionBetweenDates() {
           Date startDate =new Date(2024/9/8);
            Date endDate =new Date(2025/9/7);
            when(jdbcTemplate.update(any(String.class), any(), any())).thenReturn(1);
            String result = transactionService.deleteTransaction(startDate, endDate);

            assertEquals("removed", result);
            assertNotEquals("removed",result);
        }
        @Test
        void testByReciever(){
            Transaction transaction1=new Transaction(1555484L,new Date(2024/9/6),"Siya","Ram",5000L,"Emergency");
            Transaction transaction2=new Transaction(98746L,new Date(2023/7/6),"Shiva","Parvathi",6000L,"Emergency");
            List<Transaction> transactionsList= Stream.of(transaction1,transaction2).collect(Collectors.toList());
            when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionsList);

            List<Transaction> result=transactionService.apiByReciever("Ram");
            assertNotNull(result);
            assertEquals(2,result.size());

        }

        @Test
        void testAddransaction(){
            Transaction transaction1=new Transaction(1555484L,new Date(2024/9/6),"Siya","Ram",5000L,"Emergency");
            Transaction transaction2=new Transaction(98746L,new Date(2023/7/6),"Shiva","Parvathi",6000L,"Emergency");
            //  when(jdbcTemplate.update(anyLong(), any(Date.class), anyString(), anyString(), anyLong(), anyString())).thenReturn(0);
            Transaction result = transactionService.apiInsert(transaction1);

            assertEquals(transaction1,result);
        }


    }

