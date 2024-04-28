//package com.spring.jdbc.taskspringjdbc;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class TaskSpringjdbcApplicationTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    public TransactionService transactionService;
//
//      @Test
//    void testApprove() {
//        TransactionService transactionService = new TransactionService();
//        Transaction transaction1 = new Transaction(1, "sam", "ram", 1000, "Friend");
//        Transaction transaction2 = new Transaction(2, "Samanyu", "Shathaayu", 9000, "Family");
//        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
//
//        Transaction actual = transactionService.apiSave(transaction2);
//
////        assertEquals(transaction1.getTransactionBy(), actual.getTransactionBy());  //Fail
//        assertTrue(transaction1.getTransactionRemarks() == actual.getTransactionRemarks());    // Pass
//
//    }
//
//
//    @Test
//    void testFindBySender() {
//
//        Transaction transaction1 = new Transaction(1, "sam", "ram", 1000, "Friend");
//        Transaction transaction2 = new Transaction(2, "Samanyu", "Shathaayu", 9000, "Family");
//
//        List<Transaction> expectedList = Stream.of(transaction1,transaction2).collect(Collectors.toList());
//
//      //  when(jdbcTemplate.query(anyString(), any(Object[].class), any(transactionService.TransactionMapper.class))).thenReturn(expectedList);
//        //when(jdbcTemplate.query(anyString(),any(Object[].class),any(transactionService.TransactionMapper.class))).thenReturn(expectedList);
//
//        List<Transaction> actual = transactionService.apiFindBySender("ram");
//
////        assertFalse(expectedList.size()==actual.size());      //Fail
//        assertEquals(expectedList, actual);
//
//    }
//
//
//    @Test
//    void testFindByReceiver() {
//
//        Transaction transaction1 = new Transaction(1, "sam", "ram", 1000, "Friend");
//        Transaction transaction2 = new Transaction(2, "Samanyu", "Shathaayu", 9000, "Family");
//
//        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
//
//   //     when(jdbcTemplate.query(anyString(), any(Object[].class), any(transactionService.TransactionMapper.class))).thenReturn(expectedList);
//
//        List<Transaction> actual = transactionService.apiFindByReciever("ram");
//
//        assertSame(expectedList, actual);
//
//    }
//
//    @Test
//    void testFindByAmount() {
//
//        Transaction transaction1 = new Transaction(1, "sam", "ram", 1000, "Friend");
//        Transaction transaction2 = new Transaction(2, "Samanyu", "Shathaayu", 9000, "Family");
//
//
//        List<Transaction> expectedList = Stream.of(transaction1,transaction2).collect(Collectors.toList());
//
//       // when(jdbcTemplate.query(anyString(), any(Object[].class), any(transactionService.TransactionMapper.class))).thenReturn(expectedList);
//
//        List<Transaction> actual = transactionService.apiFindByAmount(1000);
//
////        assertFalse(expectedList.size()==actual.size());   //Fail
//
//        assertEquals(expectedList, actual);
//
//    }
//
//
//}
//
