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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import services.deposit.ServiceStatus;
import services.deposit.ViewAllDepositsResponse;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

// http://localhost:8085/module/deposits/2028001/1000.0/1


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



//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<String> handleNoHandlerFound(NoHandlerFoundException ex) {
//        return new ResponseEntity<>("Error: URL pattern does not match or a required path variable is missing.", HttpStatus.BAD_REQUEST);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//}

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(MissingPathVariableException.class)
        public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex) {
            return new ResponseEntity<>("Error: Missing path variable - " + ex.getVariableName(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/deposits/{depositId}/{amount}/{tenure}")
    public ResponseEntity<?> calculateDeposit(
            @PathVariable("depositId") Long depositId,
            @PathVariable("amount") Double amount,
            @PathVariable("tenure") Integer tenure) {

        try {
            Optional<DepositAvailable> deposit = depositInterface.searchDepositById(depositId);
            if (deposit==null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("deposit.id.unavailable"));
            }
            Double maturityAmount = amount * (1 + (deposit.get().getDepositRoi() * tenure) / 100);
            serviceStatus.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(new Object[]{deposit.get(), maturityAmount});
        } catch (DepositException e) {
            logger.error(resourceBundle.getString("deposit.error"), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("deposit.id.unavailable"));
        }
        
    }












}



