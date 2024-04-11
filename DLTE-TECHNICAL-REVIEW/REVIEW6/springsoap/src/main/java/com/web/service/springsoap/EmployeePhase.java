package com.web.service.springsoap;


import com.dao.service.daoservice.exception.EmployeeNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static java.util.Collections.addAll;

//http://localhost:8082/employeerepo/employee.wsdl

@ComponentScan("com.dao.service.daoservice")
@Endpoint

public class EmployeePhase {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private final String url="http://employee.services";
    @Autowired
    private com.dao.service.daoservice.interfaces.InputEmployeeDetails inputEmployeeDetails;
    @PayloadRoot(namespace = url,localPart = "createEmployeeRequest")
    @ResponsePayload
    public CreateEmployeeResponse createEmployee(@RequestPayload CreateEmployeeRequest createEmployeeRequest)  {
        CreateEmployeeResponse employeeResponse = new CreateEmployeeResponse();

        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = createEmployeeRequest.getEmployee();
        try {
            com.dao.service.daoservice.entity.Employee employee = new com.dao.service.daoservice.entity.Employee();
            com.dao.service.daoservice.entity.EmployeeDetails daoDetail=new com.dao.service.daoservice.entity.EmployeeDetails();
            com.dao.service.daoservice.entity.EmployeeAddress daoTemporaryAddress= new com.dao.service.daoservice.entity.EmployeeAddress();
            com.dao.service.daoservice.entity.EmployeeAddress daoPermanentAddress=new com.dao.service.daoservice.entity.EmployeeAddress();
            BeanUtils.copyProperties(actualEmployee.getEmployeeDetails(), daoDetail);
            BeanUtils.copyProperties(actualEmployee.getEmployeePermanentAddress(), daoPermanentAddress);
            BeanUtils.copyProperties(actualEmployee.getEmployeeTemporaryAddress(), daoTemporaryAddress);
            employee.setEmployeeDetails(daoDetail);
            employee.setEmployeePermanentAddress(daoPermanentAddress);
            employee.setEmployeeTemporaryAddress(daoTemporaryAddress);
            employee = inputEmployeeDetails.create(employee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            employeeResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage(resourceBundle.getString("employeeAdd.success"));
        } catch (com.dao.service.daoservice.exception.EmployeeExistsException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("employee.exist"));
        }
        employeeResponse.setServiceStatus(serviceStatus);
        return employeeResponse;
    }
    @PayloadRoot(namespace = url,localPart = "filterByIdRequest")
    @ResponsePayload
    public FilterByIdResponse filterByIdRequest(@RequestPayload FilterByIdRequest filterByIdRequest) {
        FilterByIdResponse filterByIdResponse = new FilterByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = new Employee();
        try {
           com.dao.service.daoservice.entity.Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(filterByIdRequest.getEmployeeId());
            EmployeeDetails details = new EmployeeDetails();
            EmployeeAddress temporaryAddress=new EmployeeAddress();
            EmployeeAddress permanentAddress=new EmployeeAddress();
            BeanUtils.copyProperties(employee.getEmployeeDetails(), details);
            BeanUtils.copyProperties(employee.getEmployeePermanentAddress(), permanentAddress);
            BeanUtils.copyProperties(employee.getEmployeeTemporaryAddress(), temporaryAddress);
            actualEmployee.setEmployeeDetails(details);
            actualEmployee.setEmployeePermanentAddress(permanentAddress);
            actualEmployee.setEmployeeTemporaryAddress(temporaryAddress);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            filterByIdResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage(resourceBundle.getString("employee.found"));
        } catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage(resourceBundle.getString("not.found"));
        }
        filterByIdResponse.setServiceStatus(serviceStatus);
        return filterByIdResponse;
    }

    @PayloadRoot(namespace = url,localPart = "findAllEmployeeRequest")
    @ResponsePayload
    public FindAllEmployeeResponse listAll(@RequestPayload FindAllEmployeeRequest findAllEmployeeRequest) {
        FindAllEmployeeResponse findAllEmployeeResponse=new FindAllEmployeeResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Employee> employees =new ArrayList<>();
        try {
            List<com.dao.service.daoservice.entity.Employee> daoEmployee = inputEmployeeDetails.read();
            daoEmployee.forEach(each -> {
                Employee currentEmployee = new Employee();
                EmployeeDetails details = new EmployeeDetails();
                EmployeeAddress temporaryAddress=new EmployeeAddress();
                EmployeeAddress permanentAddress=new EmployeeAddress();
                BeanUtils.copyProperties(each.getEmployeeDetails(), details);
                BeanUtils.copyProperties(each.getEmployeePermanentAddress(),permanentAddress);
                BeanUtils.copyProperties(each.getEmployeeTemporaryAddress(),temporaryAddress);
                currentEmployee.setEmployeeDetails(details);
                currentEmployee.setEmployeePermanentAddress(permanentAddress);
                currentEmployee.setEmployeeTemporaryAddress(temporaryAddress);
                employees.add(currentEmployee);
            });
            serviceStatus.setStatus( HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("display.success"));
        }catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("not.found"));
        }
        findAllEmployeeResponse.setServiceStatus(serviceStatus);
        findAllEmployeeResponse.getEmployee().addAll(employees);
        return findAllEmployeeResponse;
    }

    @PayloadRoot(namespace = url,localPart = "filterByPinRequest")
    @ResponsePayload
    public FilterByPinResponse findbyPin(@RequestPayload FilterByPinRequest filterbyPinRequest) {
        FilterByPinResponse filterByPinResponse = new FilterByPinResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> employees = new ArrayList<>();
        try {
            List<com.dao.service.daoservice.entity.Employee> daoEmployees = inputEmployeeDetails.displayBasedOnPinCode(filterbyPinRequest.getPin());
            daoEmployees.forEach(each -> {
                Employee currentEmployee = new Employee();
                EmployeeDetails details = new EmployeeDetails();
                EmployeeAddress temporaryAddress=new EmployeeAddress();
                EmployeeAddress permanentAddress=new EmployeeAddress();
                BeanUtils.copyProperties(each.getEmployeeDetails(), details);
                BeanUtils.copyProperties(each.getEmployeePermanentAddress(),permanentAddress);
                BeanUtils.copyProperties(each.getEmployeeTemporaryAddress(),temporaryAddress);
                currentEmployee.setEmployeeDetails(details);
                currentEmployee.setEmployeePermanentAddress(permanentAddress);
                currentEmployee.setEmployeeTemporaryAddress(temporaryAddress);
                employees.add(currentEmployee);
            });
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee details fetched successfully");
        } catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Employee not found");
        }
        filterByPinResponse.setServiceStatus(serviceStatus);
        filterByPinResponse.getEmployee().addAll(employees);//---------------------------------
        return filterByPinResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ve){
        Map<String, String> errors = new HashMap<>();
        ve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
