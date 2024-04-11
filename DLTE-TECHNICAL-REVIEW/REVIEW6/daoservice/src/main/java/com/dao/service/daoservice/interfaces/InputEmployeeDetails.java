package com.dao.service.daoservice.interfaces;

import com.dao.service.daoservice.entity.Employee;
import com.dao.service.daoservice.exception.EmployeeExistsException;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InputEmployeeDetails {
    Employee create( Employee employee) throws EmployeeExistsException;
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pin);
    List<Employee> read();
}
