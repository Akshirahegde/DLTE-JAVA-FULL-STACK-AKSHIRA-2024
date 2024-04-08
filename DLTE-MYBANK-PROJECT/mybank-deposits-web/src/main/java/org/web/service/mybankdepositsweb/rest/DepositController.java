package org.web.service.mybankdepositsweb.rest;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import services.deposit.ServiceStatus;
import services.deposit.ViewAllDepositsResponse;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/module")
public class DepositController {

    @Autowired
    private DepositInterface depositInterface;
    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");


    //    @GetMapping("/deposits/{depositId}")
//    public Optional<DepositAvailable> findById(@PathVariable ("depositId") long depositId, HttpServletResponse response) throws  SQLSyntaxErrorException {
//        Optional<DepositAvailable> deposit;
//
//        try {
//
//              deposit = depositInterface.searchDepositById(depositId);
//
//
//        } catch (DepositException e) {
////            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
////            logger.error(resourceBundle.getString("deposit.id.unavailable"));
////            throw e;
//        } catch (DataAccessException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            logger.error(resourceBundle.getString("internal.error"), e);
//            throw new DepositException(resourceBundle.getString("internal.error"));
//        }
//return deposit;
//    }
    // http://localhost:8083/module/deposits/2028001
    ServiceStatus serviceStatus = new ServiceStatus();

    @GetMapping("/deposits/{depositId}/{amount}/{tenure}")
    public Object[] calculateDeposit(@PathVariable("depositId") long depositId, @PathVariable("amount") double amount, @PathVariable("tenure") int tenure, HttpServletResponse response) throws DepositException {
        Optional<DepositAvailable> deposit = null;
        double maturityAmount = 0;
        try {
            deposit = depositInterface.searchDepositById(depositId);

            maturityAmount = amount * (1 + (deposit.get().getDepositRoi() * tenure) / 100);

        } catch (SQLSyntaxErrorException e) {
            System.out.println(resourceBundle.getString("internal.error"));
            logger.error(resourceBundle.getString("internal.error"));
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage(resourceBundle.getString("internal.error"));

        } catch (DepositException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error(resourceBundle.getString("deposit.id.unavailable"));
            throw e;

        }
        return new Object[]{deposit, maturityAmount};
    }
}

