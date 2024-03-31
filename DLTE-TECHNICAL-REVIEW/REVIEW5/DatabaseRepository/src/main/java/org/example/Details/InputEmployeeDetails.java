package org.example.Details;

import java.util.List;

public interface InputEmployeeDetails {
    void create(List<Employee> employee);
    Employee displayBasedOnEmployeeId(String employeeID);
    Employee displayBasedOnPinCode(int pinCode);
    List<Employee> read();
    void closeConnections();
}
