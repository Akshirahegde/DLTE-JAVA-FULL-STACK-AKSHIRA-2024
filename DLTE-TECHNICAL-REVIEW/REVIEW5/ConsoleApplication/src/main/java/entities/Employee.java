package entities;

import org.example.Details.EmployeeAddress;
import org.example.Details.EmployeeBasicDetails;

public class Employee {
    EmployeeBasicDetails employeeBasicDetails;
    EmployeeAddress employeePermanentAddress;
    EmployeeAddress employeeTemporaryAddress;

    @Override
    public String toString() {
        return "Employee{" +
                "employeebasicDetails=" + employeeBasicDetails +
                ", employeePermanentAddress=" + employeePermanentAddress +
                ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                '}';
    }
    public String displayEmployeeDetails() {
        return "Employee ID: " + employeeBasicDetails.getEmployeeId() +
                "\nName: " + employeeBasicDetails.getEmployeeName() +
                "\nEmail: " + employeeBasicDetails.getEmailId() +
                "\nPhone Number: " + employeeBasicDetails.getPhoneNumber() +
                "\nPermanent Address: " + employeePermanentAddress.getAddress() +
                "\nPermanent House Number: " + employeePermanentAddress.getHouseName() +
                "\nPermanent City: " + employeePermanentAddress.getCity() +
                "\nPermanent State: " + employeePermanentAddress.getState() +
                "\nPermanent Pin Code: " + employeePermanentAddress.getPinCode() +
                "\nTemporary Address: " + employeeTemporaryAddress.getAddress() +
                "\nTemporary House Number: " + employeeTemporaryAddress.getHouseName() +
                "\nTemporary City: " + employeeTemporaryAddress.getCity() +
                "\nTemporary State: " + employeeTemporaryAddress.getState() +
                "\nTemporary Pin Code: " + employeeTemporaryAddress.getPinCode();
    }

    public EmployeeBasicDetails getEmployeebasicDetails() {
        return employeeBasicDetails;
    }

    public void setEmployeebasicDetails(EmployeeBasicDetails employeebasicDetails) {
        this.employeeBasicDetails = employeebasicDetails;
    }

    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(EmployeeAddress employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(EmployeeAddress employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public Employee(EmployeeBasicDetails employeebasicDetails, EmployeeAddress employeePermanentAddress, EmployeeAddress employeeTemporaryAddress) {
        this.employeeBasicDetails = employeebasicDetails;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }
}

