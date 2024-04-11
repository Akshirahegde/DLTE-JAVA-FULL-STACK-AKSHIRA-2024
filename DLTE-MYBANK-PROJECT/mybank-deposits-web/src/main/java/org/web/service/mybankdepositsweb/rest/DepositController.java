package org.web.service.mybankdepositsweb.rest;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import services.deposit.ServiceStatus;
import services.deposit.ViewAllDepositsResponse;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

// http://localhost:8084/module/deposits/2028001/1000.0/1


@RestController
@RequestMapping("/module")
public class DepositController {

    @Autowired
    private DepositInterface depositInterface;
    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    ServiceStatus serviceStatus = new ServiceStatus();

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

//-----------------------------------------------------------------------------------------------------------------------------------------------------It is working properly
//    @GetMapping("/deposits/{depositId}/{amount}/{tenure}")
//    public Object[] calculateDeposit(@PathVariable("depositId") long depositId, @PathVariable("amount") double amount, @PathVariable("tenure") int tenure, HttpServletResponse response) throws DepositException {
//        Optional<DepositAvailable> deposit = null;
//        double maturityAmount = 0;
//        try {
//            deposit = depositInterface.searchDepositById(depositId);
//
//            maturityAmount = amount * (1 + (deposit.get().getDepositRoi() * tenure) / 100);
//
//        } catch (SQLSyntaxErrorException e) {
//            System.out.println(resourceBundle.getString("internal.error"));
//            logger.error(resourceBundle.getString("internal.error"));
//            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            serviceStatus.setMessage(resourceBundle.getString("internal.error"));
//
//        } catch (DepositException e) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            logger.error(resourceBundle.getString("deposit.id.unavailable"));
//            throw e;
//
//        }
//        return new Object[]{deposit, maturityAmount};
//    }
//------------------------------------------------------------------------------------------It is working properly





    @GetMapping("/deposits/{depositId}/{amount}/{tenure}")
    public ResponseEntity<Object> calculateDeposit(@PathVariable("depositId") long depositId,
                                              @PathVariable("amount") double amount,
                                              @PathVariable("tenure") int tenure) {
        try {
            Optional<DepositAvailable> deposit = depositInterface.searchDepositById(depositId);
            if (deposit.isPresent()) {
                double maturityAmount = amount * (1 + (deposit.get().getDepositRoi() * tenure) / 100);
                // Wrap both the deposit details and maturity amount in a response entity
                return ResponseEntity.ok(new Object[]{deposit.get(), maturityAmount});
            } else {
                // Deposit not found scenario
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("deposit.id.unavailable"));
            }
        } catch (DepositException e) {
            // Log the error and return a user-friendly message
            logger.error(resourceBundle.getString("deposit.error"), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBundle.getString("deposit.error"));
        } catch (SQLSyntaxErrorException e) {
            // Handle SQL syntax errors separately if needed
            logger.error("SQL error encountered", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SQL error encountered.");
        }
    }












}



