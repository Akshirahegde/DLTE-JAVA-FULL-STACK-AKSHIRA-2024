package org.web.service.mybankdepositsweb.configs;

import mybank.dao.mybankdeposit.entity.DepositAvailable;
import mybank.dao.mybankdeposit.exception.DepositException;
import mybank.dao.mybankdeposit.interfaces.DepositInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.deposit.*;


import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

//http://localhost:8085/depositrepo/deposit.wsdl
@CrossOrigin(origins = "*")
@Endpoint
@ComponentScan("mybank.dao.mybankdeposit")
public class    DepositSoap {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("details");
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
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(resourceBundle.getString("internal.error"));
            viewAllDepositsResponse.setServiceStatus(serviceStatus);
        } catch (DepositException depositException) {
            System.out.println(resourceBundle.getString("deposit.exception"));
            logger.error(resourceBundle.getString("deposit.exception"));
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("deposit.exception"));
            viewAllDepositsResponse.setServiceStatus(serviceStatus);
        }
            return viewAllDepositsResponse;


    }
    }




























































































































































//       Iterator<DepositAvailable> iterator=fromDao.iterator();
//       while (iterator.hasNext()){
//           Deposit currentDeposits= new Deposit();
//           BeanUtils.copyProperties(iterator.next(),currentDeposits);
//           actualDeposits.add(currentDeposits);
//       }