package org.example;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepository implements EmployeeDetails {
    Connection connection;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("Database");
    PreparedStatement preparedStatement;

      public DatabaseRepository() {
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isEstablished() {
        return connection != null;
    }

    @Override
    public void create(List<Employee> list) {
        for (Employee employee1 : list) {
            String employeeID = employee1.getEmployeeId();
            EmployeeAddress PermanentAddress=employee1.getAddress();
            EmployeeAddress TemporaryAddress=employee1.getAddress();
            try {
                String employees = "INSERT INTO Employee (id, name,email,phoneNumber) VALUES (?, ?,?,?)";
                preparedStatement = connection.prepareStatement(employees);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee1.getEmployeeName());
                preparedStatement.setString(3, employee1.getEmailId());
                preparedStatement.setLong(4, employee1.getPhoneNumber());
                int resultBasic = preparedStatement.executeUpdate();

              //  String permanentaddress = "INSERT INTO EmployeePermanentAddress (employeeId,permanentStreet, permanentHouseName,permanentCity, permanentState,permanentPinCode) " +
                  //      "VALUES (?, ?, ?, ?, ?, ?)";

                  String permanentaddress = "INSERT INTO EmployeePermanentAddress (employeeId,permanentStreet, permanentHouseName,permanentCity, permanentState,permanentPinCode) " +
                      "VALUES (?, ?, ?, ?, ?, ?)";

             //   preparedStatement = connection.prepareStatement(permanentaddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee1.getAddress().getPermanentStreet());
                preparedStatement.setString(3, employee1.getAddress().getPermanentHouseName());
                preparedStatement.setString(4, employee1.getAddress().getPermanentCity());
                preparedStatement.setString(5, employee1.getAddress().getPermanentState());
                preparedStatement.setLong(6, employee1.getAddress().getPermanentPinCode());
                int resultPermanent = preparedStatement.executeUpdate();

                String temporaryaddress = "INSERT INTO EmployeeTemporaryAddress(employeeId,temporaryStreet, temporaryHouseNumber,temporaryCity, temporaryState,temporaryPinCode) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(temporaryaddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee1.getAddress().getTemporaryStreet());
                preparedStatement.setString(3, employee1.getAddress().getTemporaryHouseName());
                preparedStatement.setString(4, employee1.getAddress().getTemporaryCity());
                preparedStatement.setString(5, employee1.getAddress().getTemporaryState());
                preparedStatement.setLong(6, employee1.getAddress().getTemporaryPinCode());
                int resultTemporary = preparedStatement.executeUpdate();

//                String information = "INSERT INTO EmployeeInformation (employeeId, email, phoneNumber) VALUES (?, ?, ?)";
//                preparedStatement = connection.prepareStatement(information);
//                preparedStatement.setString(1, employeeID);
//                preparedStatement.setString(2, employee1.getEmailId());
//                preparedStatement.setLong(3, employee1.getPhoneNumber());
//                int resultInformation = preparedStatement.executeUpdate();
//                connection.commit();

                if (resultBasic != 0) {
                    System.out.println("Basic details inserted");
                } else {
                    System.out.println("failed");
                }
                if (resultTemporary != 0) System.out.println("Temporary address inserted");
                if (resultPermanent != 0) System.out.println("Permanent address inserted");
               // if (resultInformation != 0) System.out.println("Additional information added");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public Employee displayBasedOnPinCode(int i) {
        return null;
    }

    @Override
    public List<Employee> read() {
        return null;
    }

}