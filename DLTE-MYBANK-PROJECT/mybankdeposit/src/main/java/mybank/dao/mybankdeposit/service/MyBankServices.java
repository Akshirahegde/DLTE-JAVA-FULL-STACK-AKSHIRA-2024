package mybank.dao.mybankdeposit.service;

import mybank.dao.mybankdeposit.entity.MyBankCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
   Logger logger= LoggerFactory.getLogger(MyBankServices.class);

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
        MyBankCustomer customer=findAllUsername().stream().filter(each ->each.getUsername().equals(username)).findFirst().orElse(null);
        if(customer==null)
            throw new UsernameNotFoundException(username);
        return customer;
    }

    public List<MyBankCustomer> findAllUsername(){
        List<MyBankCustomer> myBankCustomers=jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER",new BeanPropertyRowMapper<>(MyBankCustomer.class));
        return myBankCustomers;
    }

    public String getCustomerName(String username) {
        try {
            String sql = "SELECT customer_name FROM MYBANK_APP_CUSTOMER  WHERE username =  ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomer customers = findByUsername(username);
        if(customers==null)
            throw new UsernameNotFoundException(username);
        return customers;
    }
}













































