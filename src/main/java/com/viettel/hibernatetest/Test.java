/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hibernatetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pm2-vdi-04
 */
public class Test {
    private static String dbUrl = "jdbc:oracle:thin:@//10.60.107.89:1521/hddtdb";
    private static String dbUn = "einvoice";
    private static String dbPwd = "Viettel123";
    static private Connection connection;
    static private Statement statement;
    static private ResultSet resultSet;
    
    public static void main(String args[]) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("where is driver?");
            e.printStackTrace();
        }
        try {
            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbUrl, dbUn, dbPwd); 
//
//            // Step 2.B: Creating JDBC Statement 
            statement = (Statement) connection.createStatement();
//
//            // Step 2.C: Executing SQL &amp;amp; retrieve data into ResultSet
            resultSet = statement.executeQuery("SELECT * FROM nguyendung_persons");
            
            String s = "";
            while (resultSet.next()) {
                s += resultSet.getInt(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\n";
            }
            System.out.println(s);
            
            } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {

            // Step 3: Closing database connection
            try {
                if (null != connection) {

                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();

                    // and then finally close connection
                    connection.close();
                }
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
}
