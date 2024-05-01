package org.web.service.mybankdepositsweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("classpath:apps.properties")
public class MybankDepositsWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybankDepositsWebApplication.class, args);
    }

}
