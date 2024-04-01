package com.jdbc.soap.taskjdbcsoap;

import com.jdbc.soap.SoapPhase;
import com.jdbc.soap.Transaction;
import com.jdbc.soap.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.transaction.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTest {
    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void testSender() {
        List<Transaction> mockTransaction = new ArrayList<>();
        mockTransaction.add(new Transaction(14555L, new Date("12/02/2024"), "siya", "ram", 5000L, "Friend"));
        when(transactionService.apiBySender("siya")).thenReturn(mockTransaction);
        FindBySenderRequest request = new FindBySenderRequest();
        request.setSenderName("siya");
        FindBySenderResponse response = soapPhase.findSender(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions fetched", response.getServiceStatus().getMessage());
    }

    @Test
    public void testReciever() {
        List<Transaction> mockTransaction = new ArrayList<>();
        mockTransaction.add(new Transaction(14555L, new Date("12/02/2024"), "siya", "ram", 5000L, "Friend"));
        when(transactionService.apiByReciever("ram")).thenReturn(mockTransaction);
        FindByReceiverRequest request = new FindByReceiverRequest();
        request.setReciever("ram");
        FindByReceiverResponse response = soapPhase.findReceiver(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transactions fetched", response.getServiceStatus().getMessage());
    }
//
    @Test
    public void testAmount() {
        List<Transaction> mockTransaction = new ArrayList<>();
        mockTransaction.add(new Transaction(14555L, new Date("12/02/2024"), "siya", "ram", 5000L, "Friend"));
        when(transactionService.apiByAmount(5000,7000L)).thenReturn(mockTransaction);
        FindByAmountRequest request = new FindByAmountRequest();
        request.getMaxAmount(); //fail
        FindByAmountResponse response = soapPhase.findAmount(request);
        assertNotEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertNotEquals("Transactions available", response.getServiceStatus().getMessage());
        assertEquals(0, response.getTransaction().size());
    }
//
//
    @Test
    public void testUpdatingTransaction() {
        Transaction updateTransaction = new Transaction();
        updateTransaction.setTransactionId(15000L);
        updateTransaction.setTransactionDate(new Date(2024/9/8));
        updateTransaction.setTransactionBy("Sender");
        updateTransaction.setTransactionTo("Receiver");
        updateTransaction.setTransactionAmount(1000L);
        updateTransaction.setTransactionFor("new remark");
        when(transactionService.updateTransaction(any(Transaction.class))).thenReturn(updateTransaction);
        UpdateTransactionRequest request = new UpdateTransactionRequest();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setTransactionId(15000L);
        transaction.setTransactionDate(new Date(2024/9/8));
        updateTransaction.setTransactionBy("Sender");
        updateTransaction.setTransactionTo("Receiver");
        updateTransaction.setTransactionAmount(1000L);
        updateTransaction.setTransactionFor("old remark");
        request.setTransaction(transaction);
        UpdateTransactionResponse response = soapPhase.updateTransactionResponse(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("1 \" has been updated\"", response.getServiceStatus().getMessage());
        assertEquals(1L, response.getTransaction().getTransactionId());
        assertEquals("Updated transaction", response.getTransaction().getTransactionAmount());
    }

    @Test
    public void testRemoveTransaction() {
        Date startDate =new Date(2024/9/8);
        Date endDate =new Date(2025/9/7);
        when(transactionService.deleteTransaction(  startDate, endDate)).thenReturn("remove");
        DeleteTransactionRequest request = new DeleteTransactionRequest();
        Date start =(new Date(2024/6/8));
        Date end =(new Date(2024/9/8));
        request.setStartDate(start);
        request.setEndDate(end);
        DeleteTransactionResponse response = soapPhase.deleteTransactionResponse(request);
        assertEquals("removed", response.getServiceStatus().getStatus());
        assertEquals("removed", response.getServiceStatus().getMessage());
    }



}
