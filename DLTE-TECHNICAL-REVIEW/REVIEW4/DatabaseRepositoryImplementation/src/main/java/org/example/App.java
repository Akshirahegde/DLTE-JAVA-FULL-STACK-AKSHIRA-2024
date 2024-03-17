package org.example;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DatabaseRepository databaseRepository= new DatabaseRepository();
        if(databaseRepository.isEstablished()){
            System.out.println("connected");
        }else{
            System.out.println("failed");
        }
        List<Employee> employees=new ArrayList<>();
        Employee employee=new Employee();
        EmployeeAddress employeeAddress=new EmployeeAddress();
        employee.setEmployeeId("123");
        employee.setEmployeeName("Akshira");
        employee.setEmailId("ghsajkd");
        employee.setPhoneNumber(9387464L);
        employeeAddress.setTemporaryHouseName("sdghj");
        employeeAddress.setTemporaryStreet("ytyus");
        employeeAddress.setTemporaryCity("fgh");
        employeeAddress.setTemporaryState("fghjfg");
        employeeAddress.setTemporaryPinCode(456738);
        employeeAddress.setPermanentHouseName("rtyiue");
        employeeAddress.setPermanentStreet("tfyghwje");
        employeeAddress.setPermanentCity("tyuer");
        employeeAddress.setPermanentState("fdghj");
        employeeAddress.setPermanentPinCode(567456);
        employee.setAddress(employeeAddress);
        employees.add(employee);
        databaseRepository.create(employees);
        ///System.out.println( "Hello World!" );
    }
}
