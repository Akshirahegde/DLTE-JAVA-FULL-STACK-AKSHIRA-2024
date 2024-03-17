package org.example;

import java.util.List;

public interface EmployeeDetails {
    void create(List<Employee> employee);
    Employee displayBasedOnPinCode(int pinCode);
    List<Employee> read();
}
