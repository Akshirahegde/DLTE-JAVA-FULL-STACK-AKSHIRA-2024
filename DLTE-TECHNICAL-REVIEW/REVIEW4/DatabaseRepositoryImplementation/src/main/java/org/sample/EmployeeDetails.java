package org.sample;

import org.example.Employee;

import java.util.List;

public interface EmployeeDetails {
    void create(List<org.example.Employee> employee);
    org.example.Employee displayBasedOnPinCode(int pinCode);
    List<Employee> read();
}
