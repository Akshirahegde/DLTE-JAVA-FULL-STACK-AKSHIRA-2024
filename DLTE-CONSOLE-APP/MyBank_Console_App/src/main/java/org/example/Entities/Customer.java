package org.example.Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String userName;
    private String password;
    private String address;
    private String email;
    private Double initialBalance;
    private ArrayList transactionDetails;

    public Customer(String userName, String password, String address, String email, Double initialBalance, ArrayList transactionDetails) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.initialBalance = initialBalance;
        this.transactionDetails = transactionDetails;
    }

    public Customer(String akshira, String akshira123, String kannur, String s, long l, long l1, ArrayList<StringBuilder> transactionOne) {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public ArrayList getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(ArrayList transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", initialBalance=" + initialBalance +
                ", transactionDetails=" + transactionDetails +
                '}';
    }
}
