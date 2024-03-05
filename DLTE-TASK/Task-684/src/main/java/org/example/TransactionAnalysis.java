package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalysis {
    public static void main(String args[]) {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(new Date(2024, 02, 07), 30000, "Raksha", "Family"));
        transactionList.add(new Transaction(new Date(2023, 02, 05), 39000, "Rakshitha", "Friend"));
        transactionList.add(new Transaction(new Date(2022, 05, 12), 80000, "Rashmitha", "Emergency"));
        transactionList.add(new Transaction(new Date(2024, 01, 03), 76000, "Swasthi", "Education"));
        transactionList.add(new Transaction(new Date(2022, 05, 21), 72880, "Bhavya", "Friend"));
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose from the choices below");
        System.out.println("1.Filter based on given ranges of date\n");
        System.out.println("2.Find the least amount transferred\n");
        System.out.println("3.Find the maximum amount transferred\n");
        System.out.println("4.filter based on particular remarks");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
//                Integer endDate=null;
//                Integer startDate = null;
                transactionAnalysis.filterbyDateRange(transactionList);
                break;
            case 2:
                transactionAnalysis.leastAmountTransferred(transactionList);
                break;
            case 3:
                transactionAnalysis.maximumAmountTransferred(transactionList);
                break;
            case 4:
                transactionAnalysis.filterbyRemarks(transactionList,Comparator.comparing(Transaction::getRemarks));
                break;
        }
    }
//filter based on Date
    public void filterbyDateRange(List<Transaction> transaction){

        Integer startDate = null;
        Integer endDate=null;
        Scanner scanner=new Scanner(System.in);
        startDate=scanner.nextInt();
        endDate=scanner.nextInt();
        Integer finalStartDate = startDate;
        Integer finalEndDate = endDate;
        List<Transaction> transaction0=transaction.stream().filter(transactions->transactions.getDateOfTransaction().getDate()>= finalStartDate && transactions.getDateOfTransaction().getDate()<= finalEndDate).collect(Collectors.toList());
        transaction0.forEach(transaction8-> System.out.println(transaction8.getAmountInTransaction()));
    }
    //    least amount transferred
    public void leastAmountTransferred(List<Transaction> transaction) {
        Transaction transaction1 = transaction.stream().min(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
        System.out.println("The least amount transferred is" + transaction1.getAmountInTransaction());
    }

    //    maximum amount transferred
    public void maximumAmountTransferred(List<Transaction> transaction) {
        Transaction transaction2 = transaction.stream().max(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
        System.out.println("The maximum amount transferred is" + transaction2.getAmountInTransaction());
    }


  //    filter based on particular remarks
    public void filterbyRemarks(List<Transaction> transaction,Comparator<Transaction>comparator) {
     List<Transaction> transaction3=transaction.stream().sorted(comparator).collect(Collectors.toList());
        System.out.println(transaction3);
    }
}
