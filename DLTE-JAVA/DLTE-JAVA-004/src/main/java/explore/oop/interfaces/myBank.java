package explore.oop.interfaces;

import java.util.Scanner;

import static java.lang.System.exit;

public class myBank implements Bank {
    private Loan[] loans;
    private int loanCount;

    public myBank() {
        this.loans = new Loan[10];
        this.loanCount = 0;

    }

    public static void main(String[] args) {
        myBank bank = new myBank();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Selecty from the below choices");
            System.out.println("1.Add new loan");
            System.out.println("2.Check available loans");
            System.out.println("3.check closed loans");
            System.out.println("4.Exit");
           choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    bank.addnewLoan( getLoanDetails(scanner));
                    break;
                case 2:
                    displayLoans("Available loans", bank.checkAvailableLoan());
                    break;
                case 3:
                    displayLoans("Closed Loans", bank.checkClosedLoan());
                    break;
                case 4:
                    exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }while (choice!=4);
        scanner.close();
    }

    private static Loan getLoanDetails(Scanner scanner) {
        System.out.println("Enter Loan number");
        int loanNumber = scanner.nextInt();
        System.out.println("Enter loan Amount");
        double loanAmount = scanner.nextDouble();
        System.out.println("Enter loan Date");
        String loanDate = scanner.next();
        System.out.println("Enter loan status");
        String loanStatus = scanner.next();
        System.out.println("Enter borrower name");
        String borrowerName = scanner.next();
        System.out.println("enter borrower contact");
        String borrowerContact = scanner.next();
        return new Loan(loanNumber, loanAmount, loanDate, loanStatus, borrowerName, borrowerContact);
    }

    public void addnewLoan(Loan loan) {
        if (loanCount < loans.length) {
            loans[loanCount++] = loan;
            System.out.println("Loan added successfully");
        } else {
            System.out.println("Cannot add loan");
        }
    }

    public Loan[] checkAvailableLoan() {
        int count = 0;
        for (Loan loan : loans) {
            if (loan != null && loan.getLoanStatus().equalsIgnoreCase("Open")) {
                count++;
            }
        }


        Loan[] availableLoans = new Loan[count];
        int index = 0;
        for (Loan loan : loans) {
            if ( loan != null &&loan.getLoanStatus().equalsIgnoreCase("Open")) {
                availableLoans[index++] = loan;
            }
        }

        return availableLoans;
    }
    public Loan[] checkClosedLoan(){
        int closedCount=0;
        for(Loan loan:loans){
            if(loan!=null && loan.getLoanStatus().equalsIgnoreCase("Closed")){
                closedCount++;
            }
        }
        Loan[] closedLoans=new Loan[closedCount];
        int index=0;
        for(Loan loan:loans){
            if(loan !=null && loan.getLoanStatus().equalsIgnoreCase("Closed")){
                closedLoans[index++]=loan;
            }
        }
        return closedLoans;
    }
    private static void displayLoans(String status,Loan[]loansArray){
        System.out.println("Status:\n");
        if (loansArray.length>0) {
            for (Loan each : loansArray) {
                System.out.println(each);
            }
        }
        else{
            System.out.println("No loans found");
        }
    }
}
