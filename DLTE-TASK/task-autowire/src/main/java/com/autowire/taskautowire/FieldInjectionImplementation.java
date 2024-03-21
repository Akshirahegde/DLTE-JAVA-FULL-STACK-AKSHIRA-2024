package com.autowire.taskautowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FieldInjectionImplementation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.scan("com.autowire.taskautowire");
        applicationContext.refresh();
        MyBank myBank=applicationContext.getBean(MyBank.class);
        System.out.println(myBank.callFindAll().toString());
    }
}
