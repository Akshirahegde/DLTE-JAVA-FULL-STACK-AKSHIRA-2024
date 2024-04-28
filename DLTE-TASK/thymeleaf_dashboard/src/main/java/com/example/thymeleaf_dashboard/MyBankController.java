package com.example.thymeleaf_dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web")
public class MyBankController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String myTemplate(){
        return "index";
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String view() {
        return "view";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout() {
        return "index";
    }

}



