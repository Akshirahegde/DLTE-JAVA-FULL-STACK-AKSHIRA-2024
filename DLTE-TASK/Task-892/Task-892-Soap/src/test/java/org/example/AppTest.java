package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import application.db.Entities.Customer;
import application.db.Services.UserInfoServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import web.AccountSoap;
import web.GroupAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    private UserInfoServices userInfoServices;

    private AccountSoap accountSoap;

    @Before
    public void setUp() throws Exception {
        accountSoap = new AccountSoap();
        accountSoap.userInfoServices = userInfoServices;
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = generateCustomerList();
        when(userInfoServices.callFindAll()).thenReturn(customers);
        GroupAccount result = accountSoap.findUser("samanyu");
        verify(userInfoServices).callFindAll();
        assertEquals("Expected JSON message", generateJson(customers), result);
    }

    @Test
    public void testFindUser() {
        String username = "Samanyu";
        Customer customer = new Customer("Samanyu", "samanyu123", "karkala", "samanyu@gmail.com", 789267177L, 1000000L, new ArrayList<>());
        when(userInfoServices.callFindusername(username)).thenReturn(customer);

        GroupAccount result = accountSoap.findUser(username);

        verify(userInfoServices).callFindusername(username);
        assertEquals("Expected single customer in GroupAccount", 1, result.getCustomerList().size());
        assertEquals("Expected customer in GroupAccount", customer, result.getCustomerList().get(0));
    }

    @Test
    public void testFindByDateUserName() {
        String username = "Samanyu";
        String date = "18-03-2024";
        List<Customer> transactions = generateCustomerList();
        when(userInfoServices.callTransactionByDate(username, date)).thenReturn(transactions);

        GroupAccount result = accountSoap.findUser("samanyu");

        verify(userInfoServices).callTransactionByDate(username, date);
        assertEquals("Expected JSON message", generateJson(transactions), result);
    }

    private List<Customer> generateCustomerList() {
        List<Customer> customers = new ArrayList<>();
        List<StringBuilder> transactionOne = new ArrayList<>();
        transactionOne.add(new StringBuilder("Deposit,0,18-03-2024"));
        customers.add(new Customer("Samanyu", "samanyu123", "karkala", "samanyu@gmail.com", 789267177L, 1000000L, (ArrayList) transactionOne));
        customers.add(new Customer("Shathaayu", "shathaayu123", "Mangalore", "shathaayu@gmail", 987455335L, 1000L, (ArrayList) transactionOne));
        customers.add(new Customer("Riyaan", "riyaan123", "Mangalore", "riyaan@gmail", 987455335L, 1000L, (ArrayList) transactionOne));
        return customers;
    }

    private String generateJson(List<Customer> customers) {
        StringBuilder json = new StringBuilder("[");
        for (Customer customer : customers) {
            json.append(customerToJson(customer)).append(",");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        return json.toString();
    }

    private String customerToJson(Customer customer) {
        return String.format("{\"username\":\"%s\",\"password\":\"%s\",\"address\":\"%s\",\"email\":\"%s\",\"contact\":%d,\"initialBalace\":%d}",
                customer.getUsername(), customer.getPassword(), customer.getAddress(), customer.getEmail(), customer.getContact(), customer.getInitialBalace());
    }
}

