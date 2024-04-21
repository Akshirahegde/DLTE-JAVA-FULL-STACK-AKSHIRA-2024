package com.web.service.springsoap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.ResourceBundle;

@EnableWs
@Configuration
public class EmployeeConfig {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> servletRegistrationBean(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, resourceBundle.getString("employee.url.mapping"));// /employeerepo/*
    }

    // Configure the WSDL definition
    @Bean(name = "employee")
    public DefaultWsdl11Definition employeeWsdlDefinition(XsdSchema employeeSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName(resourceBundle.getString("employee.port"));
        wsdlDefinition.setTargetNamespace(resourceBundle.getString("employee.target.namespace"));
        wsdlDefinition.setLocationUri(resourceBundle.getString("employee.location.uri"));//webservice can be accessed
        wsdlDefinition.setSchema(employeeSchema);
        return wsdlDefinition;
    }

    // Configure the XSD schema
    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("Employee.xsd"));
    }
}

