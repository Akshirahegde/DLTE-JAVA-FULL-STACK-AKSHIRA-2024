package org.example;

import java.util.Date;

public class Transaction {
    private Date date;
    private String transactionReciever;
    private String remarks;
    private Integer amountTransferred;

    public Transaction(Date date, String transactionReciever, String remarks, Integer amountTransferred) {
        this.date = date;
        this.transactionReciever = transactionReciever;
        this.remarks = remarks;
        this.amountTransferred = amountTransferred;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(Integer amountTransferred) {
        this.amountTransferred = amountTransferred;
    }
}
