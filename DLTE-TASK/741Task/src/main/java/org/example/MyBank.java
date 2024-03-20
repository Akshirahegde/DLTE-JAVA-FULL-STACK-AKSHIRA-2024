package org.example;

import java.util.Date;

import static org.example.BankApp.loan;

public class MyBank {
    public static void main(String[] args) {
        loan.add(new Loan(123, 50000, new Date("2/2/2024"), "open", "Raksha", "810456789"));
        loan.add(new Loan(345, 90000, new Date("4/12/2023"), "open", "Rakshitha", "815456789"));
        loan.add(new Loan(156, 40000, new Date("6/2/2024"), "closed", "Bhavya", "98766789"));
        loan.add(new Loan(164, 60000, new Date("4/2/2022"), "closed", "Rashmitha", "810456789"));
        loan.add(new Loan(213, 230000, new Date("7/12/2024"), "open", "Swasthi", "984256789"));

        BankApp bankApp = ((startDate, endDate) -> {
            for (Loan loans : loan) {
                if (loans.getLoanDate().before(endDate) && loans.getLoanDate().after(startDate)) {
                    System.out.println(loans.toString());
                }
            }
        });
        bankApp.filterDate(new Date("4/12/2023"), new Date("6/2/2024"));
    }

}
