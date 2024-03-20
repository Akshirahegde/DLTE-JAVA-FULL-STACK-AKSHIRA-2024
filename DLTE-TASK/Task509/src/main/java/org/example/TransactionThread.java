package org.example;

public class TransactionThread {
    public static void main(String[] args) throws InterruptedException {
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        Thread thread1 = new Thread(transactionAnalysis, "amra");
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread(transactionAnalysis, "avni");
        thread2.start();
        Thread thread3 = new Thread(transactionAnalysis, "anvi");
        thread3.start();
        Thread thread4 = new Thread(transactionAnalysis, "arya");
        thread4.start();
        Thread thread5 = new Thread(transactionAnalysis, "ayra");
        thread5.start();
        Thread thread6 = new Thread(transactionAnalysis, "arvi");
        thread6.start();
        Thread thread7 = new Thread(transactionAnalysis, "ashi");
        thread7.start();
        Thread thread8 = new Thread(transactionAnalysis::displayAmount, "arya");
        thread8.start();
        Thread thread9 = new Thread(transactionAnalysis::displayBeneficiary, "arya");
        thread9.start();
        Thread thread10 = new Thread(transactionAnalysis::displayRemarks, "arya");
        thread10.start();


    }
}
