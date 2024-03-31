package soap.service;

import org.databaserepo.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class GroupOfEmployee {
    private List<Employee> employeeList;

    public GroupOfEmployee(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public GroupOfEmployee() {
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "GroupOfEmployee{" +
                "employeeList=" + employeeList +
                '}';
    }
}