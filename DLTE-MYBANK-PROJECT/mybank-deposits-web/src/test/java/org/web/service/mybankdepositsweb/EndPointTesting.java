package org.web.service.mybankdepositsweb;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.MyBankCustomer;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import mybank.dao.mybankdeposit.service.MyBankServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import org.web.service.mybankdepositsweb.rest.DepositController;
import org.web.service.mybankdepositsweb.security.MyBankApi;
import org.web.service.mybankdepositsweb.security.OfficialsSuccessHandler;
import services.deposit.ViewAllDepositsRequest;
import services.deposit.ViewAllDepositsResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
    @InjectMocks
    private DepositController depositController;   //rest service
    @InjectMocks
    private OfficialsSuccessHandler successHandler; //success handler

    @Mock
    private PasswordEncoder passwordEncoder;  //mybankApi
    @InjectMocks
    private MyBankApi myBankApi;              //mybankApi
    @Mock
    private MyBankServices services;          //mybankApi

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
    public void GetDepositIdByDepositName() throws Exception {
        String depositName = "Term Deposit";
        long depositId = 1L;
        when(depositInterface.getDepositIdByDepositName(depositName)).thenReturn(depositId);
        long result = depositController.getDepositIdByDepositName(depositName);
        assertEquals(depositId, result);
    }
    @Test
    public void GetDepositIdByDepositName_Failure() throws Exception {
        String depositName = "Term Deposit";
        long depositId = 1L;
        when(depositInterface.getDepositIdByDepositName(depositName)).thenReturn(depositId);
        long result = depositController.getDepositIdByDepositName(depositName);
        assertNotEquals(depositName, result);
    }
    //--------mvc controller
    @Test
    public void ShowHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/navbar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("nav"));
    }

    @Test
    public void ShowFooter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/footer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("footer"));
    }

    @Test
    public void ShowLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void ShowDeposits() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/view"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("view"));
    }

    @Test
    public void ShowDashboard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/dashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard"));
    }

    @Test
    public void ShowCalculator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userlogin/calculator"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("calculator"));
    }
    //----------successhandler(not working)
   // @Test
    void onAuthenticationSuccess_ActiveCustomer_RedirectToWsdl() throws IOException, ServletException {
        MyBankCustomer customer = new MyBankCustomer();
        customer.setCustomerStatus("active");
        customer.setAttempts(1);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(customer);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        successHandler.onAuthenticationSuccess(request, response, authentication);


        verify(response, times(1)).sendRedirect("/depositrepo/deposit.wsdl");
    }
    //-------------mybankApi

    @Test
    void save() {
        // Mock data
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







//    @Test
//    public void testCalculateDepositNotFound() throws Exception {
//        Long depositId = 2L;
//        Double amount = 1500.0;
//        Integer tenure = 3;
//        given(depositInterface.searchDepositById(depositId)).willReturn(Optional.empty());
//        mockMvc.perform(get("/deposits/2L/1000/2"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @WithMockUser(username = "Patwardhan", password = "divija123")
//    void testDepositsById() throws Exception {
//        DepositAvailable deposit1 = new DepositAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
//        DepositAvailable deposit2 = new DepositAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
//        List<DepositAvailable> mockDeposits = Stream.of(deposit1).collect(Collectors.toList());
//
//        when(depositInterface.searchDepositById(123L)).thenReturn(Optional.of(deposit1));
//
//        mockMvc.perform(get("/module/deposits/123/1000.0/2")).
//                andExpect(status().isOk());
//
//    }
//
//    @Test
//    void testApproval() throws Exception {
//        String request="{\n" +
//                "    \"depositId\": 1234,\n" +
//                "    \"depositName\": \"Fixed deposits\",\n" +
//                "    \"depositRoi\": 6.7,\n" +
//                "    \"depositType\": \"Term Deposit\",\n" +
//                "    \"depositDescription\":\"A fixed-term savings account\",\n" +
//                "}";
//
//        DepositAvailable deposit1 = new DepositAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
//        when(depositInterface.searchDepositById(123L)).thenReturn(Optional.of(deposit1));
//        mockMvc.perform(get("/module/deposits").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isUnauthorized());
//    }
//
//
//
//    //@Test
//@WithMockUser(username = "Patwardhan",password ="divija123")
//
//    public void calculateDeposit_ShouldReturnMaturityAmountAndDepositDetails() throws Exception {
//        Long depositId = 1L;
//        Double amount = 1000.0;
//        Integer tenure = 5;
//        Double expectedRoi = 10.0;
//        Double expectedMaturityAmount = 1500.0;
//        DepositAvailable depositAvailable = new DepositAvailable();
//        depositAvailable.setDepositId(depositId);
//        depositAvailable.setDepositRoi(expectedRoi);
//
//        when(depositInterface.searchDepositById(depositId)).thenReturn(Optional.of(depositAvailable));
//
//        mockMvc.perform(get(String.format( "/module/deposits/{depositId}/{amount}/{tenure}",depositId, amount, tenure))
//                .with(user("Patwardhan").password("divija123")))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.depositId", is(depositId.intValue())))
//                .andExpect((ResultMatcher) jsonPath("$.maturityAmount", is(expectedMaturityAmount)));
//    }
//    //@Test
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
////                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[1]").value(1L));
  //  }




























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