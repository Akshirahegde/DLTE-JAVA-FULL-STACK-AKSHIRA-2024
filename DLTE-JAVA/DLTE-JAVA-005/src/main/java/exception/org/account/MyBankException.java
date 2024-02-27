package exception.org.account;

import java.util.ResourceBundle;

public class MyBankException extends Exception {
    public MyBankException(String message){
        super(ResourceBundle.getBundle("application").getString("exception.account"));

    }
}
