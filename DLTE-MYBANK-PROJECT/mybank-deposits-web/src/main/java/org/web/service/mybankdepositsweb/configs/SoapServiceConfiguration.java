package org.web.service.mybankdepositsweb.configs;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Arrays;

@EnableWs
@Configuration
public class SoapServiceConfiguration extends WsConfigurerAdapter {

    //xsd to wsdl
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/depositrepo/*");
    }

    @Bean(name = "deposit")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("DepositsPort");
        defaultWsdl11Definition.setTargetNamespace("http://deposit.services");
        defaultWsdl11Definition.setLocationUri("/depositrepo/*");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }


    @Bean
    public XsdSchema loansSchema(){
        return new SimpleXsdSchema(new ClassPathResource("deposit.xsd"));
    }
}
