package com.lazybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // Load the Spring context from the XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("demo.xml");

       StudentService studentService = (StudentService) context.getBean("studentService");


//        studentService.displayStudentDetails();

    }
}