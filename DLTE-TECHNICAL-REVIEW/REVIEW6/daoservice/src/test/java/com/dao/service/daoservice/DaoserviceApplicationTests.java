package com.dao.service.daoservice;

import com.dao.service.daoservice.entity.Employee;
import com.dao.service.daoservice.entity.EmployeeAddress;
import com.dao.service.daoservice.entity.EmployeeDetails;
import com.dao.service.daoservice.exception.EmployeeNotFoundException;
import com.dao.service.daoservice.service.DatabaseRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class DaoserviceApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DatabaseRepositoryImplementation repository;

    private Employee employee;
    private EmployeeAddress tempAddress;
    private EmployeeAddress permAddress;
    private EmployeeDetails details;

    @BeforeEach
    public void setUp() {
        details = new EmployeeDetails("Samanyu", "123", "samanyu@gmail.com", 8767890987L);
        tempAddress = new EmployeeAddress("selanje", "chiguru", "karnataka", "hebri", 890987);
        permAddress = new EmployeeAddress("pakkal", "samsthitha", "karnataka", "perdoor", 987876);
        employee = new Employee(details, permAddress, tempAddress);
    }

    @Test
    void displayBasedOnEmployeeIdFound() {

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(DatabaseRepositoryImplementation.EmployeeRowMapper.class)))
                .thenReturn(employee);

        Employee foundEmployee = repository.displayBasedOnEmployeeId("123");
        assertEquals("shathaayu", foundEmployee.getEmployeeDetails().getEmployeeName());
    }

    @Test
    void displayBasedOnPinCodeFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(DatabaseRepositoryImplementation.EmployeeRowMapper.class)))
                .thenReturn(Collections.singletonList(employee));
        List<Employee> employees = repository.displayBasedOnPinCode(890987);
        assertEquals(1, employees.size());
    }

}