package soap.web.springsoapweb.configs;

import com.dao.service.daoservice.exception.EmployeeNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("com.dao.service.daoservice.interfaces")
public class EmployeePhase {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private final String url="http://employee.services";
    @Autowired
    private  com.dao.service.daoservice.interfaces.InputEmployeeDetails inputEmployeeDetails;
    @PayloadRoot(namespace = url,localPart = "createEmployeeRequest")
    @ResponsePayload
    public CreateEmployeeResponse createEmployee(@RequestPayload CreateEmployeeRequest createEmployeeRequest)  {
        CreateEmployeeResponse employeeResponse = new CreateEmployeeResponse();

        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = createEmployeeRequest.getEmployee();
        try {
            com.dao.service.daoservice.entity.Employee employee = new com.dao.service.daoservice.entity.Employee();
            BeanUtils.copyProperties(actualEmployee, employee);
            employee = inputEmployeeDetails.create(employee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            employeeResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage(resourceBundle.getString("employeeAdd.success"));
        } catch (com.dao.service.daoservice.exception.EmployeeExistsException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("employee.exist"));
        }
      //  employeeResponse.setServiceStatus(serviceStatus);
        employeeResponse.setStatus(HttpServletResponse.SC_OK);
        return employeeResponse;
    }
    @PayloadRoot(namespace = url,localPart = "filterByIdRequest")
    @ResponsePayload
    public FilterByIdResponse filterByIdRequest(@RequestPayload FilterByIdRequest findAllPayeeRequest) {
        FilterByIdResponse filterByIdResponse = new FilterByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = new Employee();
        try {
            com.dao.service.daoservice.entity.Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(findAllPayeeRequest.getEmployeeId());
            BeanUtils.copyProperties(employee, actualEmployee);
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
                BeanUtils.copyProperties(each, currentEmployee);
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
}
