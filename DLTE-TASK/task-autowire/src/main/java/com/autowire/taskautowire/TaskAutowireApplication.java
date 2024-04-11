package com.autowire.taskautowire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//@SpringBootApplication
//public class TaskAutowireApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TaskAutowireApplication.class, args);
//    }
//
//}
@SpringBootApplication
public class TaskAutowireApplication {

    public static void main(String[] args) {

        ApplicationContext appContext = SpringApplication.run(TaskAutowireApplication.class, args);
        for (String str : appContext.getBeanDefinitionNames()) {
            System.out.println(str);
        }
    }
}
