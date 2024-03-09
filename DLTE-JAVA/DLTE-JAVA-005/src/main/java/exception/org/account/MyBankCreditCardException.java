package exception.org.account;

import java.util.ResourceBundle;

public class MyBankCreditCardException extends RuntimeException {
    public MyBankCreditCardException() {
        super(ResourceBundle.getBundle("application").getString("limit.user"));
    }
}
