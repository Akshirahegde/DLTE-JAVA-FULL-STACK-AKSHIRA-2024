package org.databaseRepo;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.ResourceBundle;

public class StorageTarget {
    public static Connection connection;
    static ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    public static Connection createConn() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}