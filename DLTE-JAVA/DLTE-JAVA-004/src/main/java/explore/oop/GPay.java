package explore.oop;

import explore.oop.DebitCard;

public class GPay extends DebitCard {
    public GPay(Integer accountNumber, Integer accountBalance, String accountHolder, Integer cardNumber, String cardPin) {
        super(accountNumber, accountBalance, accountHolder, cardNumber, cardPin);
    }

    public boolean payBill(String billerName, double billedAmount, String billerType, String currentUpiPin){
        if(!validatePin(currentUpiPin)) {
            System.out.println("Bill Payment failed");
            return false;
        }
        System.out.println("GPay");
        System.out.println("Bill Payment approved for"+billerType+", Biller"+billerName+" Amount :"+billedAmount);
        return true;
    }
}
