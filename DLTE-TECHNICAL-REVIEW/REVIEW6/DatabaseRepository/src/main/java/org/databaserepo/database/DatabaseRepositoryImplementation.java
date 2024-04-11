package org.databaserepo.database;

import org.databaserepo.entity.Employee;
import org.databaserepo.entity.EmployeeAddress;
import org.databaserepo.entity.EmployeeDetails;
import org.databaserepo.exception.EmployeeException;
import org.databaserepo.exception.EmployeeExists;
import org.databaserepo.interfaces.EmployeeInputDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseRepositoryImplementation implements EmployeeInputDetails {

    Connection connection;
    ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Logger logger= LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
    ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    public DatabaseRepositoryImplementation() {
        connection=StorageTarget.createConn();
    }

@Override
public List<Employee> create(List<Employee> employeeList) {
    List<Employee> createdEmployees = new ArrayList<>(); // Create a separate list to hold newly created employees
    for(Employee employee: employeeList) {
        try {
            String employees = "INSERT INTO Employee (employeeid, employeename,  emailid ,   phonenumber) VALUES (?, ?, ?, ?)";
            preparedStatement=connection.prepareStatement(employees);
            preparedStatement.setString(1,employee.getEmployeeDetails().getEmployeeId());
            preparedStatement.setString(2,employee.getEmployeeDetails().getEmployeeName());
            preparedStatement.setString(3,employee.getEmployeeDetails().getEmailId());
            preparedStatement.setLong(4,employee.getEmployeeDetails().getPhoneNumber());

            int result=preparedStatement.executeUpdate();//------------resultBasic

            String insertTemporaryAddress = "insert into Address(EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PIN,ISTEMPORARY) values (?,?,?,?,?,?,1)";
            preparedStatement = connection.prepareStatement(insertTemporaryAddress);
            preparedStatement.setString(1, employee.getEmployeeDetails().getEmployeeId());
            preparedStatement.setString(3, employee.getEmployeeTemporaryAddress().getStreet());
            preparedStatement.setString(2, employee.getEmployeeTemporaryAddress().getHouseName());
            preparedStatement.setString(4, employee.getEmployeeTemporaryAddress().getCity());
            preparedStatement.setString(5, employee.getEmployeeTemporaryAddress().getState());
            preparedStatement.setInt(6, employee.getEmployeeTemporaryAddress().getPin());
            preparedStatement.executeUpdate();

            String insertPermanentAddress = "insert into Address(EMPLOYEEID,STREETNAME,HOUSENAME,CITYNAME,STATENAME,PIN,ISTEMPORARY) values (?,?,?,?,?,?,0)";
            preparedStatement = connection.prepareStatement(insertPermanentAddress);
            preparedStatement.setString(1, employee.getEmployeeDetails().getEmployeeId());
            preparedStatement.setString(2, employee.getEmployeePermanentAddress().getStreet());
            preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseName());
            preparedStatement.setString(4, employee.getEmployeePermanentAddress().getCity());
            preparedStatement.setString(5, employee.getEmployeePermanentAddress().getState());
            preparedStatement.setInt(6, employee.getEmployeePermanentAddress().getPin());
            preparedStatement.executeUpdate();

            createdEmployees.add(employee); // Add the created employee to the separate list

            System.out.println(resourceBundle1.getString("employee.add") +  employee.getEmployeeDetails().getEmployeeId() +" "+resourceBundle1.getString("employeeAdd.success"));
            logger.info(resourceBundle1.getString("employee.add")+  employee.getEmployeeDetails().getEmployeeId() +" "+resourceBundle1.getString("employeeAdd.success"));
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println(resourceBundle1.getString("fail.insert") + " " +  employee.getEmployeeDetails().getEmployeeId() + " " + resourceBundle1.getString("employee.exists"));
                logger.error(resourceBundle1.getString("fail.insert") + " " +  employee.getEmployeeDetails().getEmployeeId() + " " + resourceBundle1.getString("employee.exists"));
                throw new EmployeeExists(resourceBundle.getString("emp.available"));
            } else {
                e.printStackTrace();
            }
        } finally {
            closeConnections();
        }
    }
    employeeList.addAll(createdEmployees); // Add the created employees to the original list
    return employeeList;
}


    @Override
    public Employee displayBasedOnEmployeeId(String employeeID) {
        Employee employee = null;
        try {
            String query = "SELECT e.EmployeeId, e.EmployeeName, e.emailId, e.phoneNumber, ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PIN , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PIN  FROM employee e\n" +
                    " INNER JOIN Address ta ON e.EmployeeId = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    " INNER JOIN Address pa ON e.EmployeeId = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    " WHERE e.EMPLOYEEID=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employeeID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getLong(4)
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

                employee = new Employee(employeeDetails, permanentAddress,temporaryAddress);
            }else{
                throw new EmployeeException(resourceBundle1.getString("no.pincode")+ employeeID);
            }
        }  catch (SQLException| NullPointerException e) {
            throw new EmployeeException(resourceBundle1.getString("no.pincode")+ employeeID);
        }

        return employee;
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pin) {

      List<Employee> employee=new ArrayList<>();
        try {
            String query ="SELECT e.EmployeeId,e.EmployeeName,e.emailId,e.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PIN, pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PIN FROM employee e INNER JOIN Address ta ON e.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 INNER JOIN Address pa ON e.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0 WHERE ta.PIN=? or pa.PIN=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,pin);
            preparedStatement.setInt(2, pin);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails(
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("EmployeeName"),
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

                employee.add (new Employee(employeeDetails, permanentAddress,temporaryAddress));
            }
        }  catch (SQLException e) {
            throw new EmployeeException(resourceBundle1.getString("no.pincode")+ pin);
        }
        if (employee.isEmpty()) {
            throw new EmployeeException(resourceBundle1.getString("no.pincode")+ pin);
        }
        return employee;
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try {
            String findAll = "SELECT e.EmployeeId,e.EmployeeName,e.emailId,e.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PIN , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PIN FROM employee e INNER JOIN Address ta ON e.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 INNER JOIN Address pa ON e.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";

            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                EmployeeDetails employeeDetails = new EmployeeDetails(
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("EmployeeName"),
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
                employee = new Employee(employeeDetails, permanentAddress,temporaryAddress);
                employees.add(employee);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return employees;
    }

    @Override
    public void closeConnections() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public boolean Validationofdata(List<Employee> employee) {
        for (Employee employees : employee) {
            if (!isValidEmail(employees.getEmployeeDetails().getEmailId()) ||
                    !isValidPhoneNumber(employees.getEmployeeDetails().getPhoneNumber()) ||
                    !isValidPin(employees.getEmployeePermanentAddress().getPin()) ||
                    !isValidPin(employees.getEmployeeTemporaryAddress().getPin())) {
                return false;
            }
        }
        return true;
    }
    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(long phoneNumber) {
        String regex = "(\\d{10})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Long.toString(phoneNumber));
        return matcher.matches();
    }
    public static boolean isValidPin(int pin) {
        String pinString = String.valueOf(pin);
        return pinString.length() == 6;
    }
    }

