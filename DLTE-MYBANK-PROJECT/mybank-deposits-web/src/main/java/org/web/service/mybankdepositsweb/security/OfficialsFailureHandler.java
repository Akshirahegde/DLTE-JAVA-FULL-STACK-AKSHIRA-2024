package org.web.service.mybankdepositsweb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class OfficialsFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MyBankServices services;
    Logger logger= LoggerFactory.getLogger(OfficialsFailureHandler.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("details");
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyBankCustomer myBankCustomers = services.findByUsername(username);
        if(myBankCustomers!=null){
            if(!myBankCustomers.getCustomerStatus().equalsIgnoreCase("inactive")){
                if(myBankCustomers.getAttempts()< myBankCustomers.getMaxAttempts()){
                    myBankCustomers.setAttempts(myBankCustomers.getAttempts()+1);
                    services.updateAttempts(myBankCustomers);
                    logger.warn(resourceBundle.getString("invalid.credential"));
                    exception=new LockedException(resourceBundle.getString("attempt.done"));
                }
                else{
                    services.updateStatus(myBankCustomers);
                    exception=new LockedException(resourceBundle.getString("max.attempt"));
                }
            }
            else{
                logger.warn(resourceBundle.getString("account.suspend"));
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }

}
