package soap.service;

import org.databaserepo.database.DatabaseRepositoryImplementation;
import org.databaserepo.database.StorageTarget;
import org.databaserepo.entity.Employee;
import org.databaserepo.exception.EmployeeException;
import org.databaserepo.exception.EmployeeExists;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeWebImplementation  {
   // private EmployeeInputDetails employeeInputDetails;
    private DatabaseRepositoryImplementation databaseRepositoryImplementation;
public EmployeeWebImplementation(){
    StorageTarget storageTarget=new StorageTarget();
    databaseRepositoryImplementation=new DatabaseRepositoryImplementation();
   // databaseRepositoryImplementation=null;
}
    private String exceptionHandler(Exception e) {
        if (e.getClass().equals(EmployeeExists.class)) {
            return "EmployeeExistException";
        } else if (e.getClass().equals(SQLException.class)) {
            return "SQLException";
        } else if (e.getClass().equals(EmployeeException.class)) {
            return "EmployeeException";
        } else {
            return "Unknown Exception";
        }
    }

//@WebMethod
//    public void create(@WebParam(name = "Employee")List<Employee> employee) {
//    employeeInputDetails.create(employee);
//}
//
//@WebMethod
//
//    public Employee displayBasedOnEmployeeId(@WebParam(name = "EmployeeId")String employeeID) {
//        return employeeInputDetails.displayBasedOnEmployeeId(employeeID);
//    }
//@WebMethod
//@WebResult(name = "employee")
//public Employee displayBasedOnEmployeeId(@WebParam(name = "employeeId") String employeeID) {
//    Employee employees =  databaseRepositoryImplementation.displayBasedOnEmployeeId(employeeID);
//    return employees;
//}
//    @WebMethod
//    @WebResult(name = "employee")
//    public GroupOfEmployee displayBasedOnPinCode(@WebParam(name = "pinCode") int pin) {
//        List<Employee> employees = databaseRepositoryImplementation.displayBasedOnPinCode(pin);
//        return new GroupOfEmployee(employees);
//    }
//@WebMethod
//
//    public List<Employee> read() {
//        return employeeInputDetails.read();
//    }
//@WebMethod
//
//    public void closeConnections() {
//employeeInputDetails.closeConnections();
//    }
//@WebMethod
//
//    public boolean Validationofdata(List<Employee> employee) {
//        return employeeInputDetails.Validationofdata(employee);
//    }
@WebResult(name="addNewEmployee")
@WebMethod
public Employee create(@WebParam Employee employees){
    try {
        List<Employee> employee =databaseRepositoryImplementation.read();
    } catch (EmployeeException e) {
        e.printStackTrace();
    }
    return employees;
}

    @WebResult(name="findBasedOnId")
    @WebMethod
    public Employee displayBasedOnID(@WebParam String employeeId){
        Employee employee = databaseRepositoryImplementation.displayBasedOnEmployeeId(employeeId);
        return employee;
    }

    @WebResult(name="findBasedOnPincode")
    @WebMethod
    public GroupOfEmployee displayBasedOnPincode(@WebParam int pincode){
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) databaseRepositoryImplementation.displayBasedOnPinCode(pincode);
            groupOfEmployee.setEmployeeList(employees);
        } catch (EmployeeException e) {
            e.printStackTrace();
        }
        return groupOfEmployee;

    }

    @WebResult(name="findAll")
    @WebMethod
    public GroupOfEmployee read(){
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) databaseRepositoryImplementation.read();
            groupOfEmployee.setEmployeeList(employees);
        }  catch (EmployeeException e) {
            e.printStackTrace();
        }
        return groupOfEmployee;
    }
}
