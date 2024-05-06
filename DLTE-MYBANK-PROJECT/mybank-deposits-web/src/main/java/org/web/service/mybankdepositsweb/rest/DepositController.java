package org.web.service.mybankdepositsweb.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import mybank.dao.mybankdeposit.service.DepositService;
import mybank.dao.mybankdeposit.service.MyBankServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.deposit.ServiceStatus;

import javax.validation.Valid;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

// http://localhost:8085/module/deposits/2028001/1000.0/1

@RestController
@RequestMapping("/module")
@Valid
public class DepositController {
    @Autowired
    MyBankServices myBankServices;
    @Autowired
    private DepositInterface depositInterface;
    Logger logger = LoggerFactory.getLogger(DepositController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("details");
    ServiceStatus serviceStatus = new ServiceStatus();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All data fetched"),
            @ApiResponse(responseCode = "400", description = "Invalid details passed"),
            @ApiResponse(responseCode = "404", description = "No Deposit Found"),

    })
    @GetMapping("/deposits/{depositId}/{amount}/{tenure}")
    public ResponseEntity<?> calculateDeposit(
            @PathVariable("depositId") Long depositId,
         @PathVariable("amount") String amounts,
          @PathVariable("tenure")  String tenures) {
        if (!amounts.matches("^\\d*\\.?\\d+$")) {
            String errorMessage = resourceBundle.getString("amount.invalid");
            logger.warn(errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }
        Double amount = Double.valueOf(amounts);
        if (!tenures.matches("^\\d*$")) {
            String errorMessage = resourceBundle.getString("tenure.invalid");
            logger.warn(errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }
        Integer tenure = Integer.valueOf(tenures);
        try {
            Optional<DepositAvailable> deposit = depositInterface.searchDepositById(depositId);
            Double maturityAmount = amount * (1 + (deposit.get().getDepositRoi() * tenure) / 100);
            serviceStatus.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(maturityAmount);
        }
        catch (DepositException depositException) {
            logger.error(resourceBundle.getString("error.one")+depositException.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.one")+depositException.getMessage());
        }
        catch (SQLSyntaxErrorException exception) {
            logger.error(resourceBundle.getString("error.two")+exception.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.two")+exception.getMessage());
        }

        }
    @GetMapping("/deposits/id")
    public Long getDepositIdByDepositName(@RequestParam String depositName) {
        return depositInterface.getDepositIdByDepositName(depositName);
    }
    @GetMapping("/name")
    public String getCustomerName() {
        String name = getUser();
        String user = myBankServices.getCustomerName(name);
        return user;
    }
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return name;
    }
    }

























































