package com.web.service.springsoap;
//
//import com.dao.service.daoservice.entity.Employee;
//import com.dao.service.daoservice.exception.EmployeeExistsException;
//import com.dao.service.daoservice.interfaces.InputEmployeeDetails;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.ws.config.annotation.EnableWs;
//import org.springframework.ws.transport.http.MessageDispatcherServlet;
//import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
//import org.springframework.xml.xsd.SimpleXsdSchema;
//import org.springframework.xml.xsd.XsdSchema;
//
//import java.util.List;
//import java.util.ResourceBundle;
//
//@EnableWs
//@Configuration
//public class EmployeeConfig {
//    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
//    // conversion xsd to wsdl
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
//        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
//        servlet.setTransformWsdlLocations(true);
//        servlet.setApplicationContext(applicationContext);
//        return new ServletRegistrationBean(servlet,resourceBundle.getString("employee.url.mapping"));
//    }
//
//    // wsdl properties
//    @Bean(name = "employee")
//    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
//        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
//        defaultWsdl11Definition.setPortTypeName(resourceBundle.getString("employee.port"));
//        defaultWsdl11Definition.setTargetNamespace(resourceBundle.getString("employee.target.name.space"));
//        defaultWsdl11Definition.setLocationUri(resourceBundle.getString("employee.location.uri"));
//        defaultWsdl11Definition.setSchema(xsdSchema);
//        return defaultWsdl11Definition;
//    }
//
////    @Bean
////    public InputEmployeeDetails inputEmployeeDetails() {
////        return new InputEmployeeDetails() {
////            @Override
////            public Employee create(Employee employee) throws EmployeeExistsException {
////                return null;
////            }
////
////            @Override
////            public Employee displayBasedOnEmployeeId(String employeeID) {
////                return null;
////            }
////
////            @Override
////            public List<Employee> displayBasedOnPinCode(int pin) {
////                return null;
////            }
////
////            @Override
////            public List<Employee> read() {
////                return null;
////            }
////        }; // Or instantiate it using appropriate constructor
////    }
//    // identify the xsd
//    @Bean
//    public XsdSchema Schema(){
//        return new SimpleXsdSchema(new ClassPathResource("Employee.xsd"));
//    }
//}
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

    // Configure the MessageDispatcherServlet
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> servletRegistrationBean(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, resourceBundle.getString("employee.url.mapping"));
    }

    // Configure the WSDL definition
    @Bean(name = "employee")
    public DefaultWsdl11Definition employeeWsdlDefinition(XsdSchema employeeSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName(resourceBundle.getString("employee.port"));
        wsdlDefinition.setTargetNamespace(resourceBundle.getString("employee.target.namespace"));
        wsdlDefinition.setLocationUri(resourceBundle.getString("employee.location.uri"));
        wsdlDefinition.setSchema(employeeSchema);
        return wsdlDefinition;
    }

    // Configure the XSD schema
    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("Employee.xsd"));
    }
}

