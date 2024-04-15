package org.web.service.mybankdepositsweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TransactionSecurity {
    @Autowired
    private MyBankServices services;
    AuthenticationManager manager;
    @Autowired
    OfficialsFailureHandler officialsFailureHandler;
    @Autowired
    OfficialsSuccessHandler officialsSuccessHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //level 3 security
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.formLogin().usernameParameter("cus_username").failureHandler(officialsFailureHandler).successHandler(officialsSuccessHandler);
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/transactions/new").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(services);
        manager = builder.build();
        httpSecurity.authenticationManager(manager);
        return httpSecurity.build();
    }
}
