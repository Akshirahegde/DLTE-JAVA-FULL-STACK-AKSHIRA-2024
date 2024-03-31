package org.databaserepo.interfaces;

import org.databaserepo.entity.Employee;

import java.util.List;

public interface EmployeeInputDetails {
    void create(List<Employee> employee);
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pin);
    List<Employee> read();
    void closeConnections();
    boolean Validationofdata(List<Employee> employee);
}
