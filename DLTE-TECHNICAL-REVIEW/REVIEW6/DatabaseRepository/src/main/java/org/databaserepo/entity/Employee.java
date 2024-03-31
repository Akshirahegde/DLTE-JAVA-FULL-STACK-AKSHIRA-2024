package org.databaserepo.entity;

public class Employee {
    EmployeeDetails employeeDetails;
    EmployeeAddress employeePermanentAddress;
    EmployeeAddress employeeTemporaryAddress;

    public Employee(EmployeeDetails employeeDetails, EmployeeAddress employeePermanentAddress, EmployeeAddress employeeTemporaryAddress) {
        this.employeeDetails = employeeDetails;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public Employee(EmployeeDetails employeeDetails, EmployeeAddress address) {
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
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

    public Employee() {
    }
}
