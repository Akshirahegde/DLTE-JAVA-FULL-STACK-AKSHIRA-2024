package org.web.service.mybankdepositsweb.mvc;

import mybank.dao.mybankdeposit.service.MyBankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userlogin")
public class MvcController {
    @Autowired
    MyBankServices myBankServices;
//        @GetMapping("/navbar")
//        public String showHeader(){
//
//            return "nav";
//        }

//        @GetMapping("/footer")
//        public String showFooter()
//        {
//            return "footer";
//        }

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

        @GetMapping("/error")
    public String errorPage(){return "error";}
    }

