package explore.oop;

public class DebitCard extends Account {
    private Integer cardNumber;
    private  String cardPin;

    public DebitCard(int accountNumber, double accountBalance, String accountHolder, Integer cardNumber, String cardPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    //validating the pin for withdrawl
    public boolean validatePin(String currentPin){
        return cardPin.equals(currentPin);
    }
}
