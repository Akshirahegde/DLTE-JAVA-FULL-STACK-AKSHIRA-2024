package org.middleware;

import java.io.Serializable;

public class EmployeeContact implements Serializable {
    private String emailId;
    private Long phoneNumber;

    public EmployeeContact(String emailId, Long phoneNumber) {
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public EmployeeContact() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "EmployeeInformation{" +
                "emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
