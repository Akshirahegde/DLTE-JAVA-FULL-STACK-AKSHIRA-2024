package com.spring.thymeleaf_complete_webapp.controller;

import com.spring.thymeleaf_complete_webapp.entity.Transaction;
import com.spring.thymeleaf_complete_webapp.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/transaction")
@ComponentScan("com.springjdbc.driver")
public class TransactionController {
    TransactionService transactionServices;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("transaction",new Transaction());
        return "index";
    }

    @RequestMapping(value="/dash", method = RequestMethod.POST)
    public String homePage(){
        return "dashboard";
    }

    @GetMapping("/new")
    public String show(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "newTransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult bindingResult, Model model){
        model.addAttribute("transaction",transaction);
        if(!bindingResult.hasErrors()){
            Transaction transaction1=transactionServices.apiSave(transaction);
            model.addAttribute("message","Transaction successful!");
            model.addAttribute("transaction",transaction1);
            return "dashboard";
        }else{
            model.addAttribute("message","Transaction failed!");
            return "newTransaction";
        }
    }

    @GetMapping("/search")
    public String searchShow(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "filter";
    }

    @GetMapping("/results")
    public String search(@RequestParam("filter") String filterBy, @RequestParam("search") String searchTerm, Model model){
        System.out.println("Filter By:"+filterBy);
        System.out.println("Search Term:"+searchTerm);
        List<Transaction> transactionList=null;
        switch (filterBy){
            case "filterBySender":transactionList=transactionServices.apiFindBySender(searchTerm);
                break;
            case "filterByReceiver":transactionList=transactionServices.apiFindByReceiver(searchTerm);
                break;
            case "filterByAmount":transactionList=transactionServices.apiFindByAmount(Integer.parseInt(searchTerm));
                break;
        }
        model.addAttribute("transactions",transactionList);
        return "filter";
    }

    }

//    @GetMapping("/delete")
//    public String delete(@RequestParam("startDate") String startDateStr,@RequestParam("endDate") String endDateStr,Model model){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
//        Date startDate;
//        Date endDate;
//        try {
//            startDate = dateFormat.parse(startDateStr);
//            endDate =  dateFormat.parse(endDateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return "redirect:/transactions/error";
//        }
//        List<Transaction> delete=transactionServices.deleteTransaction(startDate,endDate);
//        model.addAttribute("messageDelete",delete);
//        return "index";

   // }
//}