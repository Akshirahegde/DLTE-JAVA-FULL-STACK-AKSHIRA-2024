package org.web.service.mybankdepositsweb.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            //   return ResponseEntity.ok(new Object[]{deposit.get(), maturityAmount});
            return ResponseEntity.ok(maturityAmount);
        }
        catch (DepositException depositException) {
            logger.error(resourceBundle.getString("deposit.error")+depositException.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("deposit.exception"));
        }
        catch (SQLSyntaxErrorException exception) {
            logger.error(resourceBundle.getString("syntax.error"), exception);
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }

        }
    @GetMapping("/deposits/id")
    public Long getDepositIdByDepositName(@RequestParam String depositName) {
        return depositInterface.getDepositIdByDepositName(depositName);
    }

    }



























































//@ControllerAdvice
//    public class GlobalExceptionHandler {
//
//        @ExceptionHandler(MethodArgumentNotValidException.class)
//        public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//            Map<String, String> errors = new HashMap<>();
//            ex.getBindingResult().getAllErrors().forEach((error) -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.put(fieldName, errorMessage);
//            });
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//
//        @ExceptionHandler(MissingPathVariableException.class)
//        public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex) {
//            return new ResponseEntity<>("Error: Missing path variable - " + ex.getVariableName(), HttpStatus.BAD_REQUEST);
//        }
//    }



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



