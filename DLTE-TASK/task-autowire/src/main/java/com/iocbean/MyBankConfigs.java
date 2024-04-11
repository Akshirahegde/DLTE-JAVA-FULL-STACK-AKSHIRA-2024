package com.iocbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.iocbean")
public class MyBankConfigs {
    @Bean
    public Bank getBank(){
        return new Bank();
    }
    @Bean(autowireCandidate = false,name = "mybank2")
    public Bank getBanks(){
        return new Bank("My Bank","Mumbai","mybank002");
    }
}