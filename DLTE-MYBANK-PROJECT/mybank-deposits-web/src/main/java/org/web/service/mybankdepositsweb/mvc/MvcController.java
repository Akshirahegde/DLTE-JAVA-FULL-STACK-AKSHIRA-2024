package org.web.service.mybankdepositsweb.mvc;

import mybank.dao.mybankdeposit.service.MyBankServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userlogin")
public class MvcController {
    @Autowired
    MyBankServices myBankServices;

        @GetMapping("/")
        public String showLogin(){

            return "index";
         }
         @GetMapping("/views")
        public String showDeposits(){

            return "view";
        }

        @GetMapping("/dashboards")
            public String showDashboard(){
            return "dashboard";
            }
        @GetMapping("/calculators")
             public String showcalculator(){
             return "calculator";
    }

        @GetMapping("/errors")
    public String errorPage(){return "error";}
    }

