package org.web.service.mybankdepositsweb.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userlogin")
public class MvcController {
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
    }

