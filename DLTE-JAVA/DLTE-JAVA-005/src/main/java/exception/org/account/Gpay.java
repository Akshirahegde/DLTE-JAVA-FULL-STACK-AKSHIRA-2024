package exception.org.account;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gpay extends Account {
    private String upiPin;
    private String userName;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Gpay(int accountNumber, double accountBalance, String accountHolder, String upiPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.upiPin = upiPin;
        this.userName = accountHolder;
    }
    public boolean validateUpiPin(String enteredUpiPin) throws MyBankException{
        if(!upiPin.equals(enteredUpiPin)){
            logger.log(Level.WARNING,"Invalid Upi pin entered for account number"+getAccountNumber());
            throw new MyBankException("upipin.invalid");
        }
        return true;
    }
    public void payBill(String billName, double billAmount, String billType, String upiPin) throws MyBankException {
        try{
            validateUpiPin(upiPin);
            if (getAccountBalance()>= billAmount){
                logger.log(Level.INFO,"Bill payment approved for"+billType+"biller"+billName+" Amount "+billAmount);
                setAccountBalance(getAccountBalance()-billAmount);
            }
            else {
                logger.log(Level.WARNING,"Fund insufficient for bill payment");
                throw new MyBankException("upipin.invalid");

            }
        } catch (MyBankException exception) {
          logger.log(Level.WARNING,exception.toString());
          //throw exception;
        }
    }
   // public String getUserName(){
       // return userName;
    }

