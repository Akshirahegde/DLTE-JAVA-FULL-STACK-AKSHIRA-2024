package com.security.taskrestsecurity.security;

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
public class MyBankSecurity {
        @Autowired
        MyBankUsersService myBankUsersService;
        AuthenticationManager authenticationManager;

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.httpBasic();
            httpSecurity.formLogin();
            httpSecurity.csrf().disable();
            httpSecurity.authorizeRequests().antMatchers("/user/register").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/transactions/receiver/{receiver}").hasAuthority("customer");
            httpSecurity.authorizeRequests().antMatchers("/transactions/sender/{sender}").hasAnyAuthority("customer");
            httpSecurity.authorizeRequests().antMatchers("/transactions/amount/{amount}").hasAnyAuthority("customer");
            httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin");
            httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT).hasAuthority("manager,admin");
            httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST).hasAnyAuthority("admin");
            httpSecurity.authorizeRequests().anyRequest().authenticated();
            AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
            builder.userDetailsService(myBankUsersService);
            authenticationManager=builder.build();
            httpSecurity.authenticationManager(authenticationManager);
            return httpSecurity.build();
        }
    }
