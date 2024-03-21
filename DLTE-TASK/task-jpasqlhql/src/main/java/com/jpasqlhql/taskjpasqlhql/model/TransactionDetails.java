package com.jpasqlhql.taskjpasqlhql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TransactionDetails {
    @Id
    private Integer transactionId;
    private Long transactionAmount;
    private String transactionType;
    private String transactionTo;

    public TransactionDetails(Integer transactionId, Long transactionAmount, String transactionType, String transactionTo) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionTo = transactionTo;
    }

    public TransactionDetails() {
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionAmount=" + transactionAmount +
                ", transactionType='" + transactionType + '\'' +
                ", transactionTo='" + transactionTo + '\'' +
                '}';
    }
}
