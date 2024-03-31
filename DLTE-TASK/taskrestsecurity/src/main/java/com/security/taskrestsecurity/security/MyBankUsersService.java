package com.security.taskrestsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankUsersService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public MyBankUsers signup(MyBankUsers myBankUsers){
        int ack = jdbcTemplate.update("insert into mybank_users values(?,?,?,?,?,?)",new Object[]{
                myBankUsers.getName(),
                myBankUsers.getUsername(),
                myBankUsers.getPassword(),
                myBankUsers.getEmail(),
                myBankUsers.getContact(),
                myBankUsers.getAadhaar()
        });
        return myBankUsers;
    }

    public  MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from mybank_users where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}