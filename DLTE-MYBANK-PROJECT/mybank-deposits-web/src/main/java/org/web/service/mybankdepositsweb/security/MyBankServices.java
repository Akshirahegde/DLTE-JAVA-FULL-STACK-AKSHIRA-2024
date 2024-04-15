package org.web.service.mybankdepositsweb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//http://localhost:8085/profile/register
//        {
//        "customerName": "Divija",
//        "customerAddress": "Karkala",
//        "customerStatus": "Active",
//        "customerContact": 8105431287,
//        "username": "Patwardhan",
//        "password":"divija123",
//        "attempts":1
//        }

//http://localhost:8085/depositrepo/deposit.wsdl(login)

@Service
public class MyBankServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
   Logger logger= LoggerFactory.getLogger(OfficialsFailureHandler.class);

    public MyBankCustomer signUp(MyBankCustomer myBankCustomers){
        jdbcTemplate.update("insert into MYBANK_APP_CUSTOMER values (my_bank_app_seq.nextval,?,?,?,?,?,?,?)",new Object[]{
              myBankCustomers.getCustomerName(),myBankCustomers.getCustomerAddress(),myBankCustomers.getCustomerStatus(),myBankCustomers.getCustomerContact(),myBankCustomers.getUsername(),myBankCustomers.getPassword(),myBankCustomers.getAttempts()
        });
return myBankCustomers;
    }
    public void updateAttempts(MyBankCustomer myBankCustomers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{myBankCustomers.getAttempts(),myBankCustomers.getUsername()});
        logger.info("Attempts are updated");
    }

    public void updateStatus(MyBankCustomer myBankCustomers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set customer_status='inactive' where username=?",
                new Object[]{myBankCustomers.getUsername()});
       logger.info("Status has changed");
    }
    public MyBankCustomer findByUsername(String username){
        MyBankCustomer myBankCustomers = jdbcTemplate.queryForObject("select * from MYBANK_APP_CUSTOMER where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankCustomer.class));
        return myBankCustomers;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomer customers = findByUsername(username);
        if(customers==null)
            throw new UsernameNotFoundException(username);
        return customers;
    }
}
