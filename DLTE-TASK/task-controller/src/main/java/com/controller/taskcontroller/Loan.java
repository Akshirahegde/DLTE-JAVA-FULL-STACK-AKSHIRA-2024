package com.controller.taskcontroller;

public class Loan {
    private Long  loanNumber;
    private String borrowerName;
    private Double amount;
    private Long borrowerContact;

    public Loan(long loanNumber, String borrowerName, Double amount, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.borrowerName = borrowerName;
        this.amount = amount;
        this.borrowerContact = borrowerContact;
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }
}
