package org.console;

public class EmployeeConsole {
    private String employeeName;
    private String employeeId;
    private String emailId;
    private long phoneNumber;
    EmployeeAddressConsole employeePermanentAddress;
    EmployeeAddressConsole employeeTemporaryAddress;

    public EmployeeConsole(String employeeName, String employeeId, String emailId, long phoneNumber, EmployeeAddressConsole employeePermanentAddress, EmployeeAddressConsole employeeTemporaryAddress) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public EmployeeConsole(String employeeName, String employeeId, String emailId, long phoneNumber) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public EmployeeConsole() {
    }

    public EmployeeConsole(EmployeeConsole employee1, EmployeeAddressConsole permanentDetails, EmployeeAddressConsole temporaryDetails) {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeeAddressConsole getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(EmployeeAddressConsole employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public EmployeeAddressConsole getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(EmployeeAddressConsole employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", employeePermanentAddress=" + employeePermanentAddress +
                ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                '}';
    }
}