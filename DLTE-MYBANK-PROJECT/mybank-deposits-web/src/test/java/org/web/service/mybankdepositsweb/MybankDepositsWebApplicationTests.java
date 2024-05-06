package org.web.service.mybankdepositsweb;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.MyBankCustomer;
import mybank.dao.mybankdeposit.service.DepositService;
import mybank.dao.mybankdeposit.service.MyBankServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.web.service.mybankdepositsweb.security.OfficialsFailureHandler;
import org.web.service.mybankdepositsweb.security.OfficialsSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MybankDepositsWebApplicationTests {
    @InjectMocks
    private MyBankServices myBankServices;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private OfficialsSuccessHandler customerSuccessHandler;
    @Mock
    private AuthenticationException authenticationException;

    @InjectMocks
    private OfficialsFailureHandler failureHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnAuthenticationSuccess_InactiveCustomer() throws Exception {
        MyBankCustomer customer = new MyBankCustomer();
        customer.setCustomerStatus("inactive");

        when(authentication.getPrincipal()).thenReturn(customer);

        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).encodeRedirectURL("null/userlogin/?errors= contact the admin");
    }



}
