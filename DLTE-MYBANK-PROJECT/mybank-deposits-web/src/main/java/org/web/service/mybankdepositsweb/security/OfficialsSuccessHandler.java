package org.web.service.mybankdepositsweb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OfficialsSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankServices services;

    Logger logger = LoggerFactory.getLogger(OfficialsSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException, ServletException {
        MyBankCustomer myBankCustomers = (MyBankCustomer) authentication.getPrincipal();
        if (myBankCustomers.getAttempts() != 0) {
            if (myBankCustomers.getAttempts() > 1) {
                myBankCustomers.setAttempts(1);
                services.updateAttempts(myBankCustomers);
            }
            super.setDefaultTargetUrl("http://localhost:8085/depositrepo/deposit.wsdl");
            //response.sendRedirect(request.getContextPath() + "/transactions/new");
        } else {
            logger.warn("Max attempts reached contact admin");
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}