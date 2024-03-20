package org.example;

import java.util.Date;

public class Transaction {
    // dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)
    private Date dateofTransaction;
    private Double amountInTransaction;
    private String transactionReciever;
    private String remarks;

    public Transaction(Date dateofTransaction, Double amountInTransaction, String transactionReciever, String remarks) {
        this.dateofTransaction = dateofTransaction;
        this.amountInTransaction = amountInTransaction;
        this.transactionReciever = transactionReciever;
        this.remarks = remarks;
    }

    public Date getDateofTransaction() {
        return dateofTransaction;
    }

    public void setDateofTransaction(Date dateofTransaction) {
        this.dateofTransaction = dateofTransaction;
    }

    public Double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getTransactionReciever() {
        return transactionReciever;
    }

    public void setTransactionReciever(String transactionReciever) {
        this.transactionReciever = transactionReciever;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Transaction() {
    }
}
