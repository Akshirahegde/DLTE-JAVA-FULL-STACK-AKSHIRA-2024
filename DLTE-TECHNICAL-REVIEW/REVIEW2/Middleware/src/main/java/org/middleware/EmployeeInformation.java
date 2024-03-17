package org.middleware;

import java.io.IOException;
import java.util.List;

public interface EmployeeInformation {
        void display(List<Employee> employees);
    void inputDetail() throws IOException, ClassNotFoundException;
    void read() throws IOException, ClassNotFoundException;
    void create(List<Employee> employee) throws IOException, ClassNotFoundException;
}
