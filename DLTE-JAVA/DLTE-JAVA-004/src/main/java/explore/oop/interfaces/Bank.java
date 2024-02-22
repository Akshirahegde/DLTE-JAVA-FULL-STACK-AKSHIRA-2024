package explore.oop.interfaces;

public interface Bank {
    void addnewLoan(Loan loan);
    Loan[] checkAvailableLoan();
    Loan[] checkClosedLoan();


}
