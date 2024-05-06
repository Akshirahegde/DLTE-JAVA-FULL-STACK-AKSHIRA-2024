package org.web.service.mybankdepositsweb;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.MyBankCustomer;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import mybank.dao.mybankdeposit.service.MyBankServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import org.web.service.mybankdepositsweb.mvc.MvcController;
import org.web.service.mybankdepositsweb.rest.DepositController;
import org.web.service.mybankdepositsweb.security.MyBankApi;
import org.web.service.mybankdepositsweb.security.OfficialsSuccessHandler;
import services.deposit.ViewAllDepositsRequest;
import services.deposit.ViewAllDepositsResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest


public class EndPointTesting {
    @MockBean
    private DepositInterface depositInterface;
    @InjectMocks
    DepositSoap depositSoap;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private DepositController depositController;   //rest service
    @InjectMocks
    private OfficialsSuccessHandler successHandler;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    MvcController mvcController;//success handler
    @Mock
    private Authentication authentication;

    @InjectMocks
    private OfficialsSuccessHandler officialsSuccessHandler;
    @Mock
    private PasswordEncoder passwordEncoder;  //mybankApi
    @InjectMocks
    private MyBankApi myBankApi;              //mybankApi
    @Mock
    private MyBankServices services;          //mybankApi
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mvcController).build();
    }
//--------------------------Soap------------------------------------------------
    @Test
    void ListAll() throws SQLSyntaxErrorException {
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
    void ListAll_Fail() throws SQLSyntaxErrorException {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234L, "Fixed Deposit", 23.09, "Active", "Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsRequest request = new ViewAllDepositsRequest();
        ViewAllDepositsResponse response = depositSoap.listDeposits(request);
        assertFalse(deposit2.getDepositRoi() == mockDepositList.get(0).getDepositRoi());
        assertNotNull(response.getDeposit());
    }
    @Test
    public void ListDeposits_InternalServerError() throws Exception {
        when(depositInterface.listAllDeposits()).thenThrow(new SQLSyntaxErrorException());
        ViewAllDepositsResponse response = depositSoap.listDeposits(new ViewAllDepositsRequest());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getServiceStatus().getStatus());
    }
    @Test
    public void ListDeposits_FailedInternalServerError() throws Exception {
        when(depositInterface.listAllDeposits()).thenThrow(new SQLSyntaxErrorException());
        ViewAllDepositsResponse response = depositSoap.listDeposits(new ViewAllDepositsRequest());
        assertNotEquals(HttpStatus.BAD_REQUEST.value(), response.getServiceStatus().getStatus());
    }
    @Test
    public void testListDeposits_ExceptionHandled() throws Exception {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234L, "Fixed Deposit", 23.09, "Active", "Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsResponse response = depositSoap.listDeposits(new ViewAllDepositsRequest());
        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
    }
    @Test
    public void testListDeposits_ExceptionUnHandled() throws Exception {
        List<DepositAvailable> mockDepositList = new ArrayList<>();
        DepositAvailable deposit1 = new DepositAvailable(1234L, "Fixed Deposit", 23.09, "Active", "Fixed Deposit available to the customer");
        DepositAvailable deposit2 = new DepositAvailable(4321L, "Health Savings Deposit", 12.67, "Closed", " Health Savings Deposit available to the customer");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
        ViewAllDepositsResponse response = depositSoap.listDeposits(new ViewAllDepositsRequest());
        assertNotEquals(HttpStatus.NO_CONTENT.value(), response.getServiceStatus().getStatus());
    }

//--------------------------------------------------------rest
@Test
public void CalculateDeposit_Success() throws Exception {

    long depositId = 1L;
    double amount = 1000.0;
    int tenure = 1;
    DepositAvailable depositAvailable = new DepositAvailable();
    depositAvailable.setDepositRoi(9.7);
    when(depositInterface.searchDepositById(depositId)).thenReturn(Optional.of(depositAvailable));
    ResponseEntity<?> responseEntity = depositController.calculateDeposit(depositId, String.valueOf(amount), String.valueOf(tenure));
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
}
    @Test
    public void CalculateDeposit_Failure() throws Exception {

        long depositId = 1L;
        double amount = 1000.0;
        int tenure = 1;
        DepositAvailable depositAvailable = new DepositAvailable();
        depositAvailable.setDepositRoi(9.7);
        when(depositInterface.searchDepositById(depositId)).thenReturn(Optional.of(depositAvailable));
        ResponseEntity<?> responseEntity = depositController.calculateDeposit(depositId, String.valueOf(amount), String.valueOf(tenure));
        assertNotEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    @Test
    public void CalculateDeposit_maturityAmount() throws Exception {
        long depositId = 1L;
        double amount = 1000.0;
        int tenure = 1;
        DepositAvailable depositAvailable = new DepositAvailable();
        depositAvailable.setDepositRoi(9.5);
        when(depositInterface.searchDepositById(depositId)).thenReturn(Optional.of(depositAvailable));
        ResponseEntity<?> responseEntity = depositController.calculateDeposit(depositId, String.valueOf(amount), String.valueOf(tenure));
        Double maturityAmount = (amount * (1 + (depositAvailable.getDepositRoi() * tenure) / 100));
        assertEquals(maturityAmount, responseEntity.getBody());
    }
    @Test
    public void CalculateDeposit_wrongMaturityAmount() throws Exception {
        long depositId = 1L;
        double amount = 1000.0;
        int tenure = 1;
        DepositAvailable depositAvailable = new DepositAvailable();
        depositAvailable.setDepositRoi(9.5);
        when(depositInterface.searchDepositById(depositId)).thenReturn(Optional.of(depositAvailable));
        ResponseEntity<?> responseEntity = depositController.calculateDeposit(depositId, String.valueOf(amount), String.valueOf(tenure));
        Double maturityAmount = (amount * (1 + (depositAvailable.getDepositRoi() * tenure) / 100));
        assertNotEquals(maturityAmount, responseEntity.getStatusCode());
    }
    @Test
    public void CalculateDeposit_InvalidAmount() {
        ResponseEntity<?> responseEntity = depositController.calculateDeposit(1L, "invalid", "1");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    public void CalculateDeposit_InvalidTenure() {
        ResponseEntity<?> responseEntity = depositController.calculateDeposit(1L,"567", "invalid");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void GetDepositIdByDepositName()  {
        String depositName = "Term Deposit";
        long depositId = 1L;
        when(depositInterface.getDepositIdByDepositName(depositName)).thenReturn(depositId);
        long result = depositController.getDepositIdByDepositName(depositName);
        assertEquals(depositId, result);
    }
    @Test
    public void GetDepositIdByDepositName_Failure()  {
        String depositName = "Term Deposit";
        long depositId = 1L;
        when(depositInterface.getDepositIdByDepositName(depositName)).thenReturn(depositId);
        long result = depositController.getDepositIdByDepositName(depositName);
        assertNotEquals(depositName, result);
    }

    //@Test
    void DepositException() throws Exception {
        String customerName = "Samanyu";
        String customerAddress = "Perdoor";
        String customerStatus = "Active";
        Long customerContact = 1234567890L;
        String username = "samanyu";
        String password = "samanyu123";
        Integer attempts = 0;
        Integer maxAttempts = 3;
        MyBankCustomer customer = new MyBankCustomer();
        customer.setCustomerId(123L);
        customer.setCustomerName(customerName);
        customer.setCustomerAddress(customerAddress);
        customer.setCustomerStatus(customerStatus);
        customer.setCustomerContact(customerContact);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setAttempts(attempts);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String requestBody = "{\"depositId\":456,\"depositName\":\"Savings Account\",\"depositRoi\":4.6,\"depositType\":\"Term Deposit\",\"depositDescription\":\"Fixed term Savings Account\"}";
        when(depositInterface.searchDepositById(anyLong())).thenThrow(new DepositException("dep"));
        mockMvc.perform(get("/module/deposits/12313/3213/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(content().string("No deposits Available"));
    }



    //--------mvc controller


    @Test
    public void ShowLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    @WithMockUser(username="Patwardhan")
    public void ShowViewTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/views"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("view"));
    }

    @Test
   @WithMockUser(username="Patwardhan")
    public void ShowDashboardTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/dashboards"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard"));
    }

    @Test
    @WithMockUser(username="Patwardhan")
    public void ShowCalculator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/calculators"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("calculator"));
    }
    //----------successhandler
    @Test
    public void testOnAuthenticationSuccessInactiveUser() throws IOException, ServletException {

        MyBankCustomer myBankCustomer = new MyBankCustomer();
        myBankCustomer.setCustomerStatus("inactive");
        when(authentication.getPrincipal()).thenReturn(myBankCustomer);
        officialsSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        response.encodeRedirectURL(
                "/userlogin/?errors= ontact the admin"
        );

    }

    //-------------mybankApi

    @Test
    void save() {

        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setUsername("User");
        mockCustomer.setPassword("Pass");
        when(passwordEncoder.encode(mockCustomer.getPassword())).thenReturn("encodedPassword");
        when(services.signUp(mockCustomer)).thenReturn(mockCustomer);
        MyBankCustomer savedCustomer = myBankApi.save(mockCustomer);
        verify(passwordEncoder).encode("Pass");
        verify(services).signUp(mockCustomer);
        assertEquals("User", savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming getPassword() returns the encoded password
    }
}

