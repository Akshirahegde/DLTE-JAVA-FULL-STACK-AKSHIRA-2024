package org.example;

import java.util.Date;

public class Transaction {
    private Date dateOfTransaction;
    private double amountInTransaction;
    private String transactionReciever;
    private String Remarks;

    public Transaction(Date dateOfTransaction, double amountInTransaction, String transactionReciever, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountInTransaction = amountInTransaction;
        this.transactionReciever = transactionReciever;
        Remarks = remarks;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getTransactionReciever() {
        return transactionReciever;
    }

    public void setTransactionReciever(String transactionReciever) {
        this.transactionReciever = transactionReciever;
    }

    public String getRemarks() {
        return Remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", amountInTransaction=" + amountInTransaction +
                ", transactionReciever='" + transactionReciever + '\'' +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
