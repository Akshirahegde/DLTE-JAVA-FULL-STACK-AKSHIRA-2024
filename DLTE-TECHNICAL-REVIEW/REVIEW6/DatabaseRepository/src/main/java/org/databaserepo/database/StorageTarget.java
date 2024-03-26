package org.databaserepo.database;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StorageTarget {
    public static Connection connection;
    static ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    public static Connection createConn() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return connection;
    }
}
