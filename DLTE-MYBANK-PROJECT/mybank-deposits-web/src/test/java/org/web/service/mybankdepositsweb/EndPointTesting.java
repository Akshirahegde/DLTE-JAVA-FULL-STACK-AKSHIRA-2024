package org.web.service.mybankdepositsweb;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import org.web.service.mybankdepositsweb.rest.DepositController;
import services.deposit.ViewAllDepositsRequest;
import services.deposit.ViewAllDepositsResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest(DepositController.class)
public class EndPointTesting {
    @MockBean
    private DepositInterface depositInterface;
    @InjectMocks
    DepositSoap depositSoap;
    @Mock
    JdbcTemplate jdbcTemplate;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListAll() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList= new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234L,"Fixed Deposit",23.09,"Active","Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321L,"Health Savings Deposit",12.67,"Closed"," Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsRequest request = new ViewAllDepositsRequest();
        ViewAllDepositsResponse response = depositSoap.listDeposits(request);
        assertEquals(deposit1.getDepositName(), mockDepositList.get(0).getDepositName());
        assertNotNull(response.getServiceStatus().getStatus());
    }

    @Test
    void testListAll_Fail() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList= new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234L,"Fixed Deposit",23.09,"Active","Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321L,"Health Savings Deposit",12.67,"Closed"," Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsRequest request = new ViewAllDepositsRequest();
        ViewAllDepositsResponse response = depositSoap.listDeposits(request);
        assertTrue(deposit2.getDepositRoi()==mockDepositList.get(0).getDepositRoi());       //Fail
        assertNull(response.getDeposit());     //Fail
    }

    @Test
    public void testCalculateDepositSuccess() throws Exception {
        Long depositId = 1L;
        Double amount = 1000.0;
        Integer tenure = 5;
        DepositAvailable mockDeposit = new DepositAvailable();
        mockDeposit.setDepositRoi(10.0);

        given(depositInterface.searchDepositById(depositId)).willReturn(Optional.of(mockDeposit));

        mockMvc.perform(get("/deposits/{depositId}/{amount}/{tenure}", depositId, amount, tenure))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1]").value(1500.0)); // Expected maturity amount calculation
    }

    @Test
    public void testCalculateDepositNotFound() throws Exception {
        Long depositId = 2L;
        Double amount = 1500.0;
        Integer tenure = 3;

        given(depositInterface.searchDepositById(depositId)).willReturn(Optional.empty());

        mockMvc.perform(get("/deposits/{depositId}/{amount}/{tenure}", depositId, amount, tenure))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No Deposits with the particular Id found")));
    }


    @Test
    void testCalculateDeposit_Success() throws Exception {
        DepositAvailable deposit = new DepositAvailable();
        deposit.setDepositRoi(5.0);
        when(depositInterface.searchDepositById(1L)).thenReturn(Optional.of(deposit));

        mockMvc.perform(get("/deposits/1/10000/5")
                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1]").value(12500.0));
    }
}





































//    @Test
//    void testCalculateDeposit_Success() throws Exception {
//        DepositAvailable deposit = new DepositAvailable();
//        deposit.setDepositRoi(5.0);
//        when(depositInterface.searchDepositById(1L)).thenReturn(Optional.of(deposit));
//
//        mockMvc.perform(get("/deposits/1/10000/5")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[1]").value(12500.0));
//    }
//
//    @Test
//    void testCalculateDeposit_NotFound() throws Exception {
//        when(depositInterface.searchDepositById(1L)).thenReturn(Optional.empty());
//        when(resourceBundle.getString("deposit.id.unavailable")).thenReturn("Deposit ID not available");
//
//        mockMvc.perform(get("/deposits/1/10000/5")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Deposit ID not available"));
//    }
//
//    @Test
//    void testCalculateDeposit_ThrowsException() throws Exception {
//        when(depositInterface.searchDepositById(1L)).thenThrow(new DepositException("Error fetching deposit"));
//        when(resourceBundle.getString("deposit.id.unavailable")).thenReturn("Deposit ID not available");
//
//        mockMvc.perform(get("/deposits/1/10000/5")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Deposit ID not available"));
//    }