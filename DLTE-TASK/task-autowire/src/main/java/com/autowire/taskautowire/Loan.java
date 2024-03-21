package com.autowire.taskautowire;

public class Loan {
    private Long loanNumber;
    private Long loanAmount;
    private String borrowerName;
    private Long borrowerContact;
    private String loanType;

    public Loan(Long loanNumber, Long loanAmount, String borrowerName, Long borrowerContact,String loanType) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
        this.loanType=loanType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact=" + borrowerContact +
                ", loanType='" + loanType + '\'' +
                '}';
    }
}
