/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.packo.app.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class MySQLConnUtils {
    
    //static variables
    public static final String HOSTNAME = "localhost";
    private static final String DBNAME = "packoser_packo_services_db";
    private static final String USERNAME = "packoser_admin";
    private static final String PASSWORD = "User426750!";
    
    // static variables used in local development
    // Thoses variables must be modified in the ConnectionResources.java
    // THE COMMITS SHOULD NOT INCLUDE THE ConnectionResources.java FILE!
    public static final String HOSTNAME_LOCAL = ConnectionResources.SELF_HOST;
    private static final String DBNAME_LOCAL = ConnectionResources.SELF_DB;
    private static final String USERNAME_LOCAL = ConnectionResources.SELF_USERNAME;
    private static final String PASSWORD_LOCAL = ConnectionResources.SELF_PASSWORD;
    
 public static Connection getMySQLConnection()
         throws ClassNotFoundException, SQLException {
     // Note: Change the connection parameters accordingly.
     try{
        return getMySQLConnection(HOSTNAME, DBNAME, USERNAME, PASSWORD);
     }catch(Exception e){
        System.out.println("Switching to local development basis");
        return getMySQLConnection(HOSTNAME_LOCAL, DBNAME_LOCAL, USERNAME_LOCAL, PASSWORD_LOCAL);
     }
 }
  
 public static Connection getMySQLConnection(String hostName, String dbName,
         String userName, String password) throws SQLException,
         ClassNotFoundException {
    
     Class.forName("com.mysql.jdbc.Driver");
  
     // URL Connection for MySQL:
     // Example: 
     // jdbc:mysql://localhost:3306/simplehr
     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
  
     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
}
