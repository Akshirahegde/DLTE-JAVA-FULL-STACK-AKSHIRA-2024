package org.web.service.mybankdepositsweb.security;

import mybank.dao.mybankdeposit.entity.MyBankCustomer;
import mybank.dao.mybankdeposit.service.MyBankServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        try {
            MyBankCustomer myBankCustomers = services.findByUsername(username);
            if (myBankCustomers != null) {
                if (!myBankCustomers.getCustomerStatus().equalsIgnoreCase("inactive")) {
                    if (myBankCustomers.getAttempts() < myBankCustomers.getMaxAttempts()) {
                        myBankCustomers.setAttempts(myBankCustomers.getAttempts() + 1);
                        services.updateAttempts(myBankCustomers);
                        logger.warn(resourceBundle.getString("invalid.credential"));
                        exception = new LockedException((4 - myBankCustomers.getAttempts()) + " " + resourceBundle.getString("attempts.left"));
                        String err = myBankCustomers.getAttempts().toString() + " " + exception.getMessage();
                        logger.warn(err);
                        super.setDefaultFailureUrl("/userlogin/?error=" + exception);
                    } else {
                        services.updateStatus(myBankCustomers);
                        exception = new LockedException(resourceBundle.getString("max.attempt"));
                        logger.warn(resourceBundle.getString("max.attempt"));
                        super.setDefaultFailureUrl("/userlogin/?error=" + exception.getMessage());
                    }
                } else {
                    logger.warn(resourceBundle.getString("account.suspend"));
                }
            }else {
                super.setDefaultFailureUrl("/userlogin/?error=User not exists");
            }
        }catch (UsernameNotFoundException e){
            logger.info(e.toString());
            logger.warn(resourceBundle.getString("account.suspend"));
            exception=new LockedException(resourceBundle.getString("user.not.found"));
            super.setDefaultFailureUrl("/userlogin/?error=" + exception.getMessage());
        }
        super.onAuthenticationFailure(request, response, exception);
    }

}
