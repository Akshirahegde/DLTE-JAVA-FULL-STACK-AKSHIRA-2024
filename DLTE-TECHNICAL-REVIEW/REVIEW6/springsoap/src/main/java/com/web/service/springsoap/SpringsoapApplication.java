package com.web.service.springsoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.dao.service.daoservice.interfaces")

public class SpringsoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsoapApplication.class, args);
    }

}
