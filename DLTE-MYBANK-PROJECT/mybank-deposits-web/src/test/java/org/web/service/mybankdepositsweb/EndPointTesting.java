package org.web.service.mybankdepositsweb;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import mybank.dao.mybankdeposit.service.DepositService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import services.deposit.ViewAllDepositsRequest;
import services.deposit.ViewAllDepositsResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndPointTesting {
    @MockBean
    private DepositInterface depositInterface;
    @InjectMocks
    DepositSoap depositSoap;
    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void testListAll() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList= new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234,"Fixed Deposit",23.09,"Active","Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321,"Health Savings Deposit",12.67,"Closed"," Health Savings Deposit available to the customer");
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
        DepositAvailable deposit1 = new DepositAvailable(1234,"Fixed Deposit",23.09,"Active","Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321,"Health Savings Deposit",12.67,"Closed"," Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsRequest request = new ViewAllDepositsRequest();
        ViewAllDepositsResponse response = depositSoap.listDeposits(request);
        assertTrue(deposit2.getDepositRoi()==mockDepositList.get(0).getDepositRoi());       //Fail
        assertNull(response.getDeposit());     //Fail
    }


}

