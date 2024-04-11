package com.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;


public class App {
    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("factory.xml"));
        Branch elroyBranch = beanFactory.getBean("branch3", Branch.class);
        System.out.println(elroyBranch);
        Branch akashBranch = beanFactory.getBean("branch4", Branch.class);
        System.out.println(akashBranch.getBranchContact() + " " + akashBranch.getBranchName());
        Branch elroyBranch1 = beanFactory.getBean("branch3", Branch.class);
        System.out.println(elroyBranch1);
    }
}




















































//
//public class App {
//    public static void main( String[] args )
//    {
//        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));
//        Branch akashBranch=beanFactory.getBean("branch4Service", Branch.class);
//        System.out.println(akashBranch.getBranchContact()+" "+akashBranch.getBranchName());
//        Branch elroyBranch=beanFactory.getBean("branch1Service",Branch.class);
//        System.out.println(elroyBranch);
//    }
//}