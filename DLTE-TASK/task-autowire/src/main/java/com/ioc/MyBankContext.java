package com.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class MyBankContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybank-branches.xml");
        Branch nishmithaBranch=applicationContext.getBean("branch3", Branch.class);
        System.out.println(nishmithaBranch.getIfsCode()+" "+nishmithaBranch.getBranchName());
    }
}

