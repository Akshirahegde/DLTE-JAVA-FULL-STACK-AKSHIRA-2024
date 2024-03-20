package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoanApp implements MyBank {
    Scanner scanner = new Scanner(System.in);

    public void addLoan() throws IOException, ClassNotFoundException {
        Loan loan = new Loan();
        System.out.println("Enter the Loan number");
        loan.setLoanNumber(scanner.nextInt());
        System.out.println("Enter the loan amount");
        loan.setLoanAmount(scanner.nextDouble());
        System.out.println("Enter the Loan date");
        loan.setLoanDate(scanner.next());//----------------
        System.out.println("Enter the loan status");
        loan.setLoanStatus(scanner.next());
        System.out.println("Enter the Borrower Name");
        loan.setBorrowerName(scanner.next());
        System.out.println("Enter the Borrower Contact");
        loan.setBorrowerContact(scanner.nextLong());
        loans.add(loan);//adding the loans to the arraylist
        File file = new File("Records.txt");
        ArrayList<Loan> tempLoan = new ArrayList<>();
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tempLoan.addAll((ArrayList<Loan>) objectInputStream.readObject());
        } else {
            tempLoan = loans;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        // objectOutputStream.writeObject(tempLoan);
        objectOutputStream.writeObject(tempLoan);
        fileOutputStream.close();
        objectOutputStream.close();
    }


    public void availableLoans() throws IOException, ClassNotFoundException {
        File file = new File("Records.txt");
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Loan> tempLoan = (List<Loan>) objectInputStream.readObject();
            for (Loan each : tempLoan) {
                if (each.getLoanStatus().equalsIgnoreCase("open")) {
                    System.out.println(each);
                }
            }
        }
    }

    public void closedLoans() throws IOException, ClassNotFoundException {
        File file = new File("Records.txt");
        if (file.exists()) {
            int flag = 0;
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Loan> tempLoan = (List<Loan>) objectInputStream.readObject();
            for (Loan each : loans) {
                if (each.getLoanStatus().equalsIgnoreCase("closed")) {
                    System.out.println(each);
                    flag = 1;
                }
            }
            if (flag == 0) System.out.println("No loans are closed");
        }
    }

}
