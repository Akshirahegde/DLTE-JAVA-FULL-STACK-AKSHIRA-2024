package com.spring.jdbc.taskspringjdbc;


public class Transaction {
    private Integer transactionId;
    private String transactionTo;
    private String transactionFrom;
    private Integer transactionAmount;
    private String transactionRemarks;

    public Transaction(Integer transactionId, String transactionTo, String transactionFrom, Integer transactionAmount, String transactionRemarks) {
        this.transactionId = transactionId;
        this.transactionTo = transactionTo;
        this.transactionFrom = transactionFrom;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public String getTransactionFrom() {
        return transactionFrom;
    }

    public void setTransactionFrom(String transactionFrom) {
        this.transactionFrom = transactionFrom;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionFrom='" + transactionFrom + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    public Transaction() {
    }
}
