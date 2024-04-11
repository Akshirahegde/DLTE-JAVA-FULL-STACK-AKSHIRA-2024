package com.jdbc.soap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url = "http://transaction.services";
    @Autowired
    private TransactionService transactionService;
    @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "addTransactionRequest")
    @ResponsePayload
    public AddTransactionResponse addNewTransaction(@RequestPayload AddTransactionRequest addTransactionRequest) {
        AddTransactionResponse addTransactionResponse = new AddTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        services.transaction.Transaction actual = addTransactionRequest.getTransaction();
        Date date=addTransactionRequest.getTransaction().getTransactionDate().toGregorianCalendar().getTime();
        System.out.println(actual.getTransactionId()+ " " + actual.getTransactionDate());

        Transaction daoTransaction = new Transaction();
       daoTransaction.setTransactionDate(date);
        BeanUtils.copyProperties(actual,daoTransaction);
        System.out.println(daoTransaction);

        daoTransaction = transactionService.apiInsert(daoTransaction);

        if (daoTransaction!=null) {
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransaction, actual);
            addTransactionResponse.setTransaction(actual);
            serviceStatus.setMessage(actual.getTransactionId() + " inserted");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId() + " is not inserted");
        }
        addTransactionResponse.setServiceStatus(serviceStatus);
        return addTransactionResponse;


//        AddTransactionResponse addTransactionResponse=new AddTransactionResponse();
//        ServiceStatus serviceStatus=new ServiceStatus();
//        services.transaction.Transaction actual=addTransactionRequest.getTransaction();
//        Transaction transaction=new Transaction();
//        BeanUtils.copyProperties(actual,transaction);
//        transaction=transactionService.apiInsert(transaction);
//
//        if(transaction!=null){
//            serviceStatus.setStatus("SUCCESS");
//            BeanUtils.copyProperties(transaction,actual);
//            addTransactionResponse.setTransaction(actual);
//            serviceStatus.setMessage(actual.getTransactionId()+" has inserted");
//        }
//        else{
//            serviceStatus.setStatus("FAILURE");
//            serviceStatus.setMessage(actual.getTransactionId()+" hasn't inserted");
//        }
//        addTransactionResponse.setServiceStatus(serviceStatus);
//        return addTransactionResponse;
    }
    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "findBySenderRequest")
    @ResponsePayload
    public FindBySenderResponse findSender(@RequestPayload FindBySenderRequest findBySenderRequest) {
        FindBySenderResponse findBySenderResponse = new FindBySenderResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransactions = new ArrayList<>();

        List<Transaction> received = transactionService.apiBySender(findBySenderRequest.getSenderName());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions fetched");

        findBySenderResponse.setServiceStatus(serviceStatus);
        findBySenderResponse.getTransaction().addAll(returnTransactions);

        return findBySenderResponse;
    }
    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "findByReceiverRequest")
    @ResponsePayload
    public FindByReceiverResponse findReceiver(@RequestPayload FindByReceiverRequest findByReceiverRequest) {
        FindByReceiverResponse findByReceiverResponse = new FindByReceiverResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransactions = new ArrayList<>();

        List<Transaction> received = transactionService.apiByReciever(findByReceiverRequest.getReciever());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions fetched");

        findByReceiverResponse.setServiceStatus(serviceStatus);
        findByReceiverResponse.getTransaction().addAll(returnTransactions);

        return findByReceiverResponse;
    }
    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "findByAmountRequest")
    @ResponsePayload
    public FindByAmountResponse findAmount(@RequestPayload FindByAmountRequest findByAmountRequest) {
        FindByAmountResponse findByAmountResponse = new FindByAmountResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> returnTransactions = new ArrayList<>();

        List<Transaction> received = transactionService.apiByAmount(findByAmountRequest.getMinAmount(),findByAmountRequest.getMaxAmount());

        Iterator<Transaction> iterator = received.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransactions = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transactions fetched");

        findByAmountResponse.setServiceStatus(serviceStatus);
        findByAmountResponse.getTransaction().addAll(returnTransactions);

        return findByAmountResponse;
    }
    @PreAuthorize("hasAnyAuthority('admin','manager')")
    @PayloadRoot(namespace = url, localPart = "updateTransactionRequest")
    @ResponsePayload
    public UpdateTransactionResponse updateTransactionResponse(@RequestPayload UpdateTransactionRequest updateRemarksRequest) {
        UpdateTransactionResponse updateRemarksResponse = new UpdateTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        services.transaction.Transaction transaction = new services.transaction.Transaction();


        Transaction daoTransaction = new Transaction();
        BeanUtils.copyProperties(updateRemarksRequest.getTransaction(), daoTransaction);

        daoTransaction = transactionService.updateTransaction(daoTransaction);

        if (daoTransaction!=null) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransaction.getTransactionBy() + " has updated the remarks");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransaction.getTransactionBy() + " has not updated the remarks");
        }

        BeanUtils.copyProperties(daoTransaction,transaction);

        updateRemarksResponse.setServiceStatus(serviceStatus);
        updateRemarksResponse.setTransaction(transaction);

        return updateRemarksResponse;

    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "deleteTransactionRequest")
    @ResponsePayload
    public DeleteTransactionResponse deleteTransactionResponse(@RequestPayload DeleteTransactionRequest deleteTransactionRequest) {
        DeleteTransactionResponse response = new DeleteTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Date startDate=deleteTransactionRequest.getStartDate().toGregorianCalendar().getTime();
        Date endDate=deleteTransactionRequest.getEndDate().toGregorianCalendar().getTime();
        String fromDAO = transactionService.deleteTransaction(startDate, endDate);
        if (fromDAO.contains("Invalid")) {
            serviceStatus.setStatus("FAILURE");
        } else
            serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage(fromDAO);
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
