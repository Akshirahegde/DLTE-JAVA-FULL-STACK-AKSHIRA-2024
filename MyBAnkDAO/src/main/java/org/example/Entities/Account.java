package org.example.Entities;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String address;
    private String email;
    private Long contact;
    private Long initialBalace;
    private ArrayList transactionDetails;

    public Account(String username, String password, String address, String email, Long contact, Long initialBalace, ArrayList transactionDetails) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.initialBalace = initialBalace;
        this.transactionDetails = transactionDetails;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Long getInitialBalace() {
        return initialBalace;
    }

    public void setInitialBalace(Long initialBalace) {
        this.initialBalace = initialBalace;
    }

    public ArrayList getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(ArrayList transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}
