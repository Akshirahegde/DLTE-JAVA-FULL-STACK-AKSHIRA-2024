package org.example;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis implements Runnable {
    Transaction transaction[] = {
            new Transaction(new Date(2023, 02, 04), "Raksha", "Family", 5678),
            new Transaction(new Date(2024, 05, 06), "Rakshitha", "Friend", 6688),
            new Transaction(new Date(2013, 02, 14), "Rashmitha", "Emergengy", 9678),
            new Transaction(new Date(2023, 06, 04), "Swasthi", "Medical", 4578),
            new Transaction(new Date(2022, 03, 07), "Bhavya", "Family", 4298),
    };

    public void run() {
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select your choice");
            System.out.println("1.Filter based on given ranges of date\n2.Find least amount transferred\n3.Find maximum amount transferred\n4.Find number of transaction made to particular beneficiary\n5.filter based on particular remark\n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    transactionAnalysis.filterDate(transaction);
                    break;
                case 2:
                    transactionAnalysis.leastAmountTransferred(transaction);
                    break;
                case 3:
                    transactionAnalysis.maxAmountTransferred(transaction);
                    break;
                case 4:
                    transactionAnalysis.numberOfTransaction(transaction);
                    break;
                case 5:
                    transactionAnalysis.filterRemarks(transaction);
                    break;


            }
        }

    }

    public void filterDate(Transaction[] transaction) {
        String dateInput;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date(yyyy/mm/dd)");
        dateInput = scanner.next();
        String splitDate[] = dateInput.split("/");
        for (Transaction each : transaction) {
            if ((Integer.parseInt(splitDate[0]) == (each.getDate().getYear())) && (Integer.parseInt(splitDate[1]) == (each.getDate().getMonth())) && (Integer.parseInt(splitDate[2]) == (each.getDate().getDate()))) {
                System.out.println("Transaction was made to" + each.getTransactionReciever());
            }

        }

    }

    public void leastAmountTransferred(Transaction[] transaction) {
        int leastAmount = 0;
        for (Transaction each : transaction) {
            leastAmount = each.getAmountTransferred();
            if (leastAmount > each.getAmountTransferred()) {
                leastAmount = each.getAmountTransferred();
            }

        }
        System.out.println("The least amount transferred is" + leastAmount);

    }

    public void maxAmountTransferred(Transaction[] transaction) {
        int maxAmount = 0;
        for (Transaction each : transaction) {
            maxAmount = each.getAmountTransferred();
            if (maxAmount < each.getAmountTransferred()) {
                maxAmount = each.getAmountTransferred();
            }

        }
        System.out.println("The max amount transferred is" + maxAmount);
    }

    public void numberOfTransaction(Transaction[] transaction) {
        Scanner scanner = new Scanner(System.in);
        String beneficiary;
        int numberOfTransaction = 0;
        System.out.println("Enter the beneficiary name\n");
        beneficiary = scanner.next();
        for (Transaction each : transaction) {
            if (each.getTransactionReciever().equals(beneficiary)) {
                numberOfTransaction++;
            }
        }
        System.out.println("the no of transaction made to" + beneficiary + " is " + numberOfTransaction);
    }

    public void filterRemarks(Transaction[] transaction) {
        Scanner scanner = new Scanner(System.in);
        String remark;
        System.out.println("Enter the remark");
        remark = scanner.next();
        for (Transaction each : transaction) {
            if (each.getRemarks().equals(remark)) {
                System.out.println("The amount transferred to beneficiary is" + each.getAmountTransferred() + "having remarks" + remark);
            }

        }

    }

    public void displayBeneficiary() {
        for (Transaction each : transaction)
            System.out.println("The Beneficiaries are" + each.getTransactionReciever());
    }

    public void displayRemarks() {
        for (Transaction each : transaction)
            System.out.println("The Remarks are" + each.getRemarks());
    }

    public void displayAmount() {
        for (Transaction each : transaction)
            System.out.println("The amount transferred is" + each.getAmountTransferred());
    }
}
