package soap.service;

import org.databaserepo.database.DatabaseRepositoryImplementation;
import org.databaserepo.database.StorageTarget;
import org.databaserepo.entity.Employee;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeWebImplementation  {
   // private EmployeeInputDetails employeeInputDetails;
    private DatabaseRepositoryImplementation databaseRepositoryImplementation;
public EmployeeWebImplementation(){
    StorageTarget storageTarget=new StorageTarget();
    databaseRepositoryImplementation=new DatabaseRepositoryImplementation();
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
@WebMethod
@WebResult(name = "employee")
public Employee displayBasedOnEmployeeId(@WebParam(name = "employeeId") String employeeID) {
    Employee employees =  databaseRepositoryImplementation.displayBasedOnEmployeeId(employeeID);
    return employees;
}
    @WebMethod
    @WebResult(name = "employee")
    public GroupOfEmployee displayBasedOnPinCode(@WebParam(name = "pinCode") int pin) {
        List<Employee> employees = databaseRepositoryImplementation.displayBasedOnPinCode(pin);
        return new GroupOfEmployee(employees);
    }
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
}
