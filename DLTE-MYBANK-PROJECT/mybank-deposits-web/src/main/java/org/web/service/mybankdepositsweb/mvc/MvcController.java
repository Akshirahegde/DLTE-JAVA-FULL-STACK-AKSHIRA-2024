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
        @GetMapping("/navbar")
        public String showHeader(){

            return "nav";
        }

        @GetMapping("/footer")
        public String showFooter()
        {
            return "footer";
        }

        @GetMapping("/")
        public String showLogin(){

            return "index";
         }
         @GetMapping("/view")
        public String showDeposits(){

            return "view";
        }

        @GetMapping("/dashboard")
            public String showDashboard(){

                return "dashboard";
            }
    @GetMapping("/calculator")
    public String showcalculator(){

        return "calculator";
    }
//    @GetMapping("/name")
//    @ResponseBody
//    public String customerName(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        return myBankServices.findByUsername(name).getCustomerName();
//    }

    }

