//package com.web.service.springsoap;
//
//import com.dao.service.daoservice.entity.Employee;
//import com.dao.service.daoservice.exception.EmployeeExistsException;
//import com.dao.service.daoservice.interfaces.InputEmployeeDetails;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import services.employee.CreateEmployeeRequest;
//import services.employee.CreateEmployeeResponse;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class SpringsoapApplicationTests {
//
//    @ExtendWith(MockitoExtension.class)
//
//
//        @Mock
//        private InputEmployeeDetails inputEmployeeDetails;
//
//        @InjectMocks
//        private EmployeePhase employeePhase;
//
//        @Test
//        public void CreateEmployee_success() throws EmployeeExistsException {
//            CreateEmployeeRequest request = new CreateEmployeeRequest();
//            request.setEmployee(new services.employee.Employee());
//            services.employee.EmployeeDetails details = new services.employee.EmployeeDetails();
//            request.getEmployee().setEmployeeDetails(details);
//
//            Employee daoEmployee = new Employee();
//            when(inputEmployeeDetails.create(any(Employee.class))).thenReturn(daoEmployee);
//
//            CreateEmployeeResponse response = employeePhase.createEmployee(request);
//
//            assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
//            assertEquals("Employee added successfully", response.getServiceStatus().getMessage());
//        }
//
//        @Test
//        public void testCreateEmployeeExistsException() throws EmployeeExistsException {
//            CreateEmployeeRequest request = new CreateEmployeeRequest();
//            request.setEmployee(new services.employee.Employee());
//
//            when(inputEmployeeDetails.create(any(Employee.class))).thenThrow(EmployeeExistsException.class);
//
//            CreateEmployeeResponse response = employeePhase.createEmployee(request);
//
//            assertEquals(HttpStatus.NO_CONTENT.value(), response.getServiceStatus().getStatus());
//            assertEquals("Employee already exists", response.getServiceStatus().getMessage());
//        }
//
//
//}
