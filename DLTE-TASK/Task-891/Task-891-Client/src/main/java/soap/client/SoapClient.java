package soap.client;

import org.soap.AccountSoap;
import org.soap.AccountSoapService;
import org.soap.Customer;

import java.util.List;

public class SoapClient {
    public static void main(String[] args) {
        AccountSoapService service = new AccountSoapService();
        AccountSoap accountSoap = service.getAccountSoapPort();
        List<Customer> cards = accountSoap.findByUsername("Eeksha").getCustomerList();
        for (Customer each : cards) {
            System.out.println(each.getUsername() + " " + each.getPassword() + " " + each.getInitialBalace());
        }

    }
}

