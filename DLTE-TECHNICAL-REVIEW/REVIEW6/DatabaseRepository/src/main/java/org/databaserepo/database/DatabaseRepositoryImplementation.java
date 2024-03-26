package org.databaserepo.database;

import org.databaserepo.entity.Employee;
import org.databaserepo.entity.EmployeeAddress;
import org.databaserepo.entity.EmployeeDetails;
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
    public void create(List<Employee> employeeList) {

        for(Employee employee:employeeList){

            String employeeID=employee.getEmployeeDetails().getEmployeeId();
            try{
                String employees = "INSERT INTO Employee (id, name) VALUES (?, ?)";
                preparedStatement=connection.prepareStatement(employees);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeDetails().getEmployeeName());
                int result=preparedStatement.executeUpdate();//------------resultBasic

                String permanentaddress = "INSERT INTO EmployeeAddress (employeeId,permanentStreet, permanentHouseName,permanentCity, permanentState,permanentPin) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";//---------EmployeeAddress
                preparedStatement=connection.prepareStatement(permanentaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeePermanentAddress().getStreet());
                preparedStatement.setString(3,employee.getEmployeePermanentAddress().getHouseName());
                preparedStatement.setString(4,employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeePermanentAddress().getPin());
                int resultPermanent=preparedStatement.executeUpdate();

                String temporaryaddress = "INSERT INTO EmployeeTemporaryAddress(employeeId,temporaryStreet, temporaryHouseName,temporaryCity, temporaryState,temporaryPin) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(temporaryaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeTemporaryAddress().getStreet());
                preparedStatement.setString(3,employee.getEmployeeTemporaryAddress().getHouseName());
                preparedStatement.setString(4,employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeeTemporaryAddress().getPin());
                int resultTemporary=preparedStatement.executeUpdate();

                String information = "INSERT INTO EmployeeInformation (employeeId, email, phoneNumber) VALUES (?, ?, ?)";
                preparedStatement=connection.prepareStatement(information);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeDetails().getEmailId());
                preparedStatement.setLong(3,employee.getEmployeeDetails().getPhoneNumber() );
                int resultInformation=preparedStatement.executeUpdate();
                System.out.println(resourceBundle1.getString("employee.add")+" " + employeeID +" "+resourceBundle1.getString("employeeAdd.success"));

            }catch (SQLException sqlException) {
                if (sqlException instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println(resourceBundle1.getString("fail.insert") +" "+ employeeID + " "+resourceBundle1.getString("employee.exists"));
                } else {
                    sqlException.printStackTrace();
                }
            }
        }


    }

    @Override
    public Employee displayBasedOnEmployeeId(String employeeID) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM employee emp INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId WHERE emp.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employeeID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentStreet"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPin")
                );

                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryStreet"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPin")
                );

                employee = new Employee(employeeDetails, permanentAddress, temporaryAddress);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee displayBasedOnPinCode(int pin) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM employee emp INNER JOIN EmployeeInformation empinf ON emp.id=empinf.employeeId INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId WHERE empPAdd.permanentPin= ? OR empTAdd.temporaryPin = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,pin);
            preparedStatement.setInt(2, pin);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentStreet"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPin")
                );

                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryStreet"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPin")
                );

                employee = new Employee(employeeDetails, permanentAddress, temporaryAddress);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try {
            String findAll = "SELECT * FROM employee emp INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                EmployeeDetails employeeDetails = new EmployeeDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentStreet"),
                        resultSet.getString("permanentHouseName"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPin")
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryStreet"),
                        resultSet.getString("temporaryHouseName"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPin")
                );

                employee = new Employee(employeeDetails, permanentAddress, temporaryAddress);
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

