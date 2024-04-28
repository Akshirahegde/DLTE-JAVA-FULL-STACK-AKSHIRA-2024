//package com.spring.jdbc.taskspringjdbc;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//public class EndpointTest{
//
//    @MockBean
//    private TransactionService transactionService;
//
//    @InjectMocks
//    private Controller controller;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void testAproval() throws Exception {
//        String request = "{\n" +
//                "        \"transactionId\": 1,\n" +
//                "        \"transactionTo\": \"Ram\",\n" +
//                "        \"transactionFrom\": \"Sam\",\n" +
//                "        \"transactionAmount\": 9000,\n" +
//                "        \"transactionRemarks\": \"Friend\"\n" +
//                "    }";
//
//        Transaction transaction = new Transaction(1, "Ram","Sam",9000,"Friend");
//        when(transactionService.apiSave(any())).thenReturn(transaction);
//
//        mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(1)).
//                andExpect(jsonPath("$.transactionTo").value("Ram")).
//                andExpect(jsonPath("$.transactionFrom").value("Sam")).
//                andExpect(jsonPath("$.transactionAmount").value(9000)).
//                andExpect(jsonPath("$.transactionRemarks").value("Friend"));
//
//    }
//
//    @Test
//    void testFindBySender() throws Exception {
//        Transaction transaction1 = new Transaction(1, "Ram","Sam",9000,"Friend");
//        List<Transaction> list = Stream.of(transaction1).collect(Collectors.toList());
//
//        when(transactionService.apiFindBySender(eq("Sam"))).thenReturn(list);
//
//        mockMvc.perform(get("/transaction/sender")).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(1)).
//                andExpect(jsonPath("$.transactionTo").value("Ram")).
//                andExpect(jsonPath("$.transactionFrom").value("Sam")).
//                andExpect(jsonPath("$.transactionAmount").value(9000)).
//                andExpect(jsonPath("$.transactionRemarks").value("Friend"));
//    }
//
//
//    @Test
//    void testFindByReceiver() throws Exception {
//        Transaction transaction1 = new Transaction(1, "Ram","Sam",9000,"Friend");
//        List<Transaction> list = Stream.of(transaction1).collect(Collectors.toList());
//
//        when(transactionService.apiFindByReciever(eq("Ram"))).thenReturn(list);
//
//        mockMvc.perform(get("/transaction/receiver")).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(1)).
//                andExpect(jsonPath("$.transactionTo").value("Ram")).
//                andExpect(jsonPath("$.transactionFrom").value("Sam")).
//                andExpect(jsonPath("$.transactionAmount").value(9000)).
//                andExpect(jsonPath("$.transactionRemarks").value("Friend"));
//    }
//
//    @Test
//    void testFindByAmount() throws Exception {
//        Transaction transaction1 = new Transaction(1, "Ram","Sam",9000,"Friend");
//        List<Transaction> list = Stream.of(transaction1).collect(Collectors.toList());
//
//        when(transactionService.apiFindByAmount(eq(9000))).thenReturn(list);
//
//        mockMvc.perform(get("/transaction/view")).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(1)).
//                andExpect(jsonPath("$.transactionTo").value("Ram")).
//                andExpect(jsonPath("$.transactionFrom").value("Sam")).
//                andExpect(jsonPath("$.transactionAmount").value(9000)).
//                andExpect(jsonPath("$.transactionRemarks").value("Friend"));
//
//    }
//
//}