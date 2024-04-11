package com.dao.service.daoservice.service;

import com.dao.service.daoservice.entity.Employee;
import com.dao.service.daoservice.entity.EmployeeAddress;
import com.dao.service.daoservice.entity.EmployeeDetails;
import com.dao.service.daoservice.exception.EmployeeExistsException;
import com.dao.service.daoservice.exception.EmployeeNotFoundException;
import com.dao.service.daoservice.interfaces.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
@Service
public class DatabaseRepositoryImplementation implements InputEmployeeDetails {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);

    ResourceBundle resourceBundle= ResourceBundle.getBundle("dao");


    @Override
    public Employee create(Employee employee) throws EmployeeExistsException {

        String employeeID = employee.getEmployeeDetails().getEmployeeId();

        try {
            String employeesQuery = "INSERT INTO Employee (EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(employeesQuery, employeeID, employee.getEmployeeDetails().getEmployeeName(),
                    employee.getEmployeeDetails().getEmailId(), employee.getEmployeeDetails().getPhoneNumber());

            // Inserting into Address table
            String insertTemporaryAddressQuery = "INSERT INTO Address(EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PIN,ISTEMPORARY) VALUES (?,?,?,?,?,?,1)";
            jdbcTemplate.update(insertTemporaryAddressQuery, employeeID, employee.getEmployeeTemporaryAddress().getStreet(),
                    employee.getEmployeeTemporaryAddress().getHouseName(), employee.getEmployeeTemporaryAddress().getCity(),
                    employee.getEmployeeTemporaryAddress().getState(), employee.getEmployeeTemporaryAddress().getPin());

            String insertPermanentAddressQuery = "INSERT INTO Address(EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PIN,ISTEMPORARY) VALUES (?,?,?,?,?,?,0)";
            jdbcTemplate.update(insertPermanentAddressQuery, employeeID, employee.getEmployeePermanentAddress().getStreet(),
                    employee.getEmployeePermanentAddress().getHouseName(), employee.getEmployeePermanentAddress().getCity(),
                    employee.getEmployeePermanentAddress().getState(), employee.getEmployeePermanentAddress().getPin());

            System.out.println(resourceBundle.getString("employee.add") + employeeID + " " + resourceBundle.getString("employeeAdd.success"));
            logger.info(resourceBundle.getString("employee.add") + employeeID + " " + resourceBundle.getString("employeeAdd.success"));
            return employee;
        } catch (DuplicateKeyException e) {
            logger.warn(resourceBundle.getString("Fail.insert") + " " + employeeID + " " + resourceBundle.getString("employee.exists"));
            throw new EmployeeExistsException(resourceBundle.getString("employee.exists"));
        }
    }


    @Override
    public Employee displayBasedOnEmployeeId(String employeeID) {
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PIN , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PIN  FROM employee emp\n" +
                    "INNER JOIN Address ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN Address pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "WHERE emp.EMPLOYEEID=?";
            return jdbcTemplate.queryForObject(query, new Object[]{employeeID}, new EmployeeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn(resourceBundle.getString("no.employee") + employeeID);
            throw new EmployeeNotFoundException(resourceBundle.getString("no.employee") + employeeID);
        }
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pin) {
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PIN , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PIN  FROM employee emp\n" +
                    "INNER JOIN Address ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN Address pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "WHERE ta.PIN=? or pa.PIN=?";
            List<Employee> employees = jdbcTemplate.query(query, new Object[]{pin, pin}, new EmployeeRowMapper());
            if (employees.isEmpty()) {
                logger.warn(resourceBundle.getString("no.pin") + pin);
                throw new EmployeeNotFoundException(resourceBundle.getString("no.pin") + pin);
            }
            return employees;
        } catch (EmptyResultDataAccessException e) {
            logger.warn(resourceBundle.getString("no.pin") + pin);
            throw new EmployeeNotFoundException(resourceBundle.getString("no.pin") + pin);
        } catch (DataAccessException e) {
            logger.warn(resourceBundle.getString("no.pin") + pin);
            throw new EmployeeNotFoundException(resourceBundle.getString("no.pin") + pin);
        }
    }

    @Override
    public List<Employee> read() {
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PIN , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PIN  FROM employee emp\n" +
                    "INNER JOIN Address ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN Address pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";
            return jdbcTemplate.query(query, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException(resourceBundle.getString("fail.fetch"), e);
        }
    }

    public class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            EmployeeDetails basicDetails = new EmployeeDetails(
                    resultSet.getString("EmployeeName"),
                    resultSet.getString("EmployeeId"),
                    resultSet.getString("emailId"),
                    resultSet.getLong("phoneNumber")
            );

            EmployeeAddress permanentAddress = new EmployeeAddress(
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getString(12),
                    resultSet.getString(13),
                    resultSet.getInt(14)
            );

            EmployeeAddress temporaryAddress = new EmployeeAddress(
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getInt(9)
            );

            return new Employee(basicDetails, permanentAddress, temporaryAddress);
        }
    }
}
