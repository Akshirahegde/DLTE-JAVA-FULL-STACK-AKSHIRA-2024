package org.web.service.mybankdepositsweb.security;

import mybank.dao.mybankdeposit.entity.MyBankCustomer;
import mybank.dao.mybankdeposit.service.MyBankServices;
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
import java.util.ResourceBundle;

@Component
public class OfficialsSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankServices services;

    Logger logger = LoggerFactory.getLogger(OfficialsSuccessHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("details");
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException, ServletException {
        MyBankCustomer myBankCustomers = (MyBankCustomer) authentication.getPrincipal();
        if (!myBankCustomers.getCustomerStatus().equalsIgnoreCase("inactive")) {
            if (myBankCustomers.getAttempts() > 1) {
                myBankCustomers.setAttempts(1);
                services.updateAttempts(myBankCustomers);
            }
           //super.setDefaultTargetUrl("/depositrepo/deposit.wsdl");
            super.setDefaultTargetUrl("/userlogin/dashboard");
       //     super.setDefaultTargetUrl("/transaction/head");
        } else {
            logger.warn(resourceBundle.getString("attempt.end"));
            //super.setDefaultTargetUrl("/login");
            super.setDefaultTargetUrl("/userlogin/?errors="+ " contact the admin");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}