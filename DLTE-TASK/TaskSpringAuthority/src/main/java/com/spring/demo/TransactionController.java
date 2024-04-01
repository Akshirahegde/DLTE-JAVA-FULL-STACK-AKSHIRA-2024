package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);


    @PostMapping("/")
    public TransactionsModel callSave(@RequestBody TransactionsModel transactionsModel){
        TransactionsModel transactionsModel1 = null;
        try{
            transactionsModel1 =  transactionServices.apiSave(transactionsModel);
        }catch (TransactionException transactionException){
            logger.warn(transactionException.toString());
        }

        return transactionsModel1;
    }


    @GetMapping("/findBy/{by}")
    public List<TransactionsModel> callFindBy(@PathVariable("by") String by){
        return transactionServices.apiFindBySender(by);
    }



    @GetMapping("/findTo/{to}")
    public List<TransactionsModel> callFindTo(@PathVariable("to") String to){
        return transactionServices.apiFindByReceiver(to);
    }



    @GetMapping("/findAmount/{amount}")
    public List<TransactionsModel> callFindBy(@PathVariable("amount") Long amount){
        return transactionServices.apiFindByAmount(amount);
    }



    @PutMapping("/updateRemarks")
    public TransactionsModel callUpdateTransaction(@RequestBody TransactionsModel transactionsModel){
        TransactionsModel transaction1= transactionServices.apiUpdate(transactionsModel);
        return transaction1;
    }

    @DeleteMapping("/deleteByDate/{start}/{end}")
    public String callDeleteTransaction(@PathVariable("start") String start,@PathVariable("end") String end) throws ParseException {
        SimpleDateFormat dateFormatStart = new SimpleDateFormat(start);
        Date dateStart = dateFormatStart.parse(start);
        SimpleDateFormat dateFormatEnd = new SimpleDateFormat(end);
        Date dateEnd = dateFormatStart.parse(end);
        String result = transactionServices.removeByDate(dateStart,dateEnd);
        if(result.contains("removed"))
            return result +" transaction between "+ start + " to "+end;
        else
            return result +" not removed";

    }
}