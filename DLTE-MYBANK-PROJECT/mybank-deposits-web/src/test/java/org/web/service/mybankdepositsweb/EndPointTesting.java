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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import services.deposit.ViewAllDepositsRequest;
import services.deposit.ViewAllDepositsResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testListAll() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234L, "Fixed Deposit", 23.09, "Active", "Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsRequest request = new ViewAllDepositsRequest();
        ViewAllDepositsResponse response = depositSoap.listDeposits(request);
        assertEquals(deposit1.getDepositName(), mockDepositList.get(0).getDepositName());
        assertNotNull(response.getServiceStatus().getStatus());
    }

    @Test
    void testListAll_Fail() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234L, "Fixed Deposit", 23.09, "Active", "Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsRequest request = new ViewAllDepositsRequest();
        ViewAllDepositsResponse response = depositSoap.listDeposits(request);
        assertTrue(deposit2.getDepositRoi() == mockDepositList.get(0).getDepositRoi());       //Fail
        assertNull(response.getDeposit());     //Fail
    }


    @Test
    public void testCalculateDepositNotFound() throws Exception {
        Long depositId = 2L;
        Double amount = 1500.0;
        Integer tenure = 3;

        given(depositInterface.searchDepositById(depositId)).willReturn(Optional.empty());

        mockMvc.perform(get("/deposits/2L/1000/2"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(username = "Patwardhan", password = "divija123")
    void testDepositsById() throws Exception {
        DepositAvailable deposit1 = new DepositAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
        DepositAvailable deposit2 = new DepositAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
        List<DepositAvailable> mockDeposits = Stream.of(deposit1).collect(Collectors.toList());

        when(depositInterface.searchDepositById(123L)).thenReturn(Optional.of(deposit1));

        mockMvc.perform(get("/module/deposits/123/1000.0/2")).
                andExpect(status().isOk());
//                andExpect(jsonPath("$.depositId").value(123)).
//                andExpect(jsonPath("$.depositName").value("Fixed Savings")).
//                andExpect(jsonPath("$.depositRoi").value(4.5)).
//                andExpect(jsonPath("$.depositType").value("Term Deposit"));

    }


@Test
@WithMockUser(username = "Patwardhan",password ="divija123")

    public void calculateDeposit_ShouldReturnMaturityAmountAndDepositDetails() throws Exception {
        Long depositId = 1L;
        Double amount = 1000.0;
        Integer tenure = 5;
        Double expectedRoi = 10.0;
        Double expectedMaturityAmount = 1500.0;

        DepositAvailable depositAvailable = new DepositAvailable();
        depositAvailable.setDepositId(depositId);
        depositAvailable.setDepositRoi(expectedRoi);

        when(depositInterface.searchDepositById(depositId)).thenReturn(Optional.of(depositAvailable));

        mockMvc.perform(get(String.format( "/module/deposits/{depositId}/{amount}/{tenure}",depositId, amount, tenure))
                .with(user("Patwardhan").password("divija123")))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.depositId", is(depositId.intValue())))
                .andExpect((ResultMatcher) jsonPath("$.maturityAmount", is(expectedMaturityAmount)));
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

//@Test
//    public void testCalculateDepositSuccess() throws Exception {
//        Long depositId = 1L;
//        Double amount = 1000.0;
//        Integer tenure = 5;
//        DepositAvailable mockDeposit = new DepositAvailable();
//        mockDeposit.setDepositRoi(10.0);
//
//        given(depositInterface.searchDepositById(depositId)).willReturn(Optional.of(mockDeposit));
//
//        mockMvc.perform(get("/deposits/{depositId}/{amount}/{tenure}", depositId, amount, tenure))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[1]").value(1L));
//    }