package org.middleware;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeFirstName;
    private String employeeMiddlename;
    private String employeeLastName;
    private  EmployeeAddress address;
    private EmployeeContact employeeContact;


    public Employee(String employeeFirstName, String employeeMiddlename, String employeeLastName, EmployeeAddress address, EmployeeContact employeeContact) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddlename = employeeMiddlename;
        this.employeeLastName = employeeLastName;
        this.address = address;
        this.employeeContact = employeeContact;
    }

    public Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddlename='" + employeeMiddlename + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", address=" + address +
                ", employeeContact=" + employeeContact +
                '}';
    }

    public EmployeeAddress getAddress() {
        return address;
    }

    public void setAddress(EmployeeAddress address) {
        this.address = address;
    }

    public EmployeeContact getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(EmployeeContact employeeContact) {
        this.employeeContact = employeeContact;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeMiddlename() {
        return employeeMiddlename;
    }

    public void setEmployeeMiddlename(String employeeMiddlename) {
        this.employeeMiddlename = employeeMiddlename;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }
}

