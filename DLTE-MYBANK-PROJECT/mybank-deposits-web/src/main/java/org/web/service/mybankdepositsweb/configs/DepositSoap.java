package org.web.service.mybankdepositsweb.configs;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.entity.DepositAvailed;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.deposit.*;


import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

//http://localhost:8084/depositrepo/deposit.wsdl

@Endpoint
@ComponentScan("mybank.dao.mybankdeposit")
public class    DepositSoap {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(DepositSoap.class);
    private final String url = "http://deposit.services";

    @Autowired
    private DepositInterface depositInterface;

    @PayloadRoot(namespace = url, localPart = "viewAllDepositsRequest")
    @ResponsePayload
    public ViewAllDepositsResponse listDeposits(@RequestPayload ViewAllDepositsRequest viewAllDepositsRequest) {
        ViewAllDepositsResponse viewAllDepositsResponse = new ViewAllDepositsResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<DepositAvailable> fromDao = null;
        try {
            fromDao = depositInterface.listAllDeposits();
            List<Deposit> actualDeposits = new ArrayList<>();

            fromDao.forEach(each -> {
                Deposit currentDeposits = new Deposit();
                BeanUtils.copyProperties(each, currentDeposits);
                actualDeposits.add(currentDeposits);
            });

            viewAllDepositsResponse.getDeposit().addAll(actualDeposits);
            serviceStatus.setMessage(resourceBundle.getString("fetch.success"));
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            viewAllDepositsResponse.setServiceStatus(serviceStatus);

        } catch (SQLSyntaxErrorException e) {
            System.out.println(resourceBundle.getString("internal.error"));
            logger.error(resourceBundle.getString("internal.error"));
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage(resourceBundle.getString("internal.error"));
            viewAllDepositsResponse.setServiceStatus(serviceStatus);
        } catch (DepositException depositException) {
            System.out.println(resourceBundle.getString("deposit.exception"));
            logger.error(resourceBundle.getString("deposit.exception"));
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("deposit.exception"));
            viewAllDepositsResponse.setServiceStatus(serviceStatus);
        } finally {
            return viewAllDepositsResponse;
        }

    }
//
//    @PayloadRoot(namespace = url, localPart = "searchByIdRequest")
//    @ResponsePayload
//    @GetMapping("/searchDepositById")
////    @RequestPayload SearchByIdRequest searchByIdRequest
//    public SearchByIdResponse searchDepositById(@RequestParam Long Id){
//        SearchByIdResponse searchByIdResponse=new SearchByIdResponse();
//        ServiceStatus serviceStatus = new ServiceStatus();
//        Optional<DepositAvailable> fromDao = null;
//            try {
//
//                fromDao=depositInterface.searchDepositById(Id);
//
//                if (fromDao != null) {
//                    double depositMaturity = calculateMaturityAmount(fromDao);
//                   // searchByIdResponse.setDeposit(depositMaturity);
//                    serviceStatus.setStatus(HttpServletResponse.SC_OK);
//                    serviceStatus.setMessage(resourceBundle.getString("fetch.success"));
//                } else {
//                    serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                    serviceStatus.setMessage(resourceBundle.getString("deposit.not.found"));
//                }
//            } catch (Exception e) {
//                serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                serviceStatus.setMessage(resourceBundle.getString("internal.error"));
//                logger.error("Error occurred while searching deposit by ID", e);
//            }
//
//        searchByIdResponse.setStatus(serviceStatus);
//
//            return searchByIdResponse;
//        }
//
//
//        public static double calculateMaturityAmount( DepositAvailed deposit,DepositAvailable deposits) {
//            double principalAmount = deposit.getDepositAmount();
//            double rate = deposits.getDepositRoi();
//            int time = deposit.getDepositDuration();
//
//            double depositMaturity = principalAmount + (principalAmount * rate * time) / 100;
//
//            return depositMaturity;
//        }




    }




























































































































































//       Iterator<DepositAvailable> iterator=fromDao.iterator();
//       while (iterator.hasNext()){
//           Deposit currentDeposits= new Deposit();
//           BeanUtils.copyProperties(iterator.next(),currentDeposits);
//           actualDeposits.add(currentDeposits);
//       }