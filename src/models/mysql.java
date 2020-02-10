/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sony
 */
public class mysql {
    public static Connection connect;
    private static String DB_HOST;
    private static String DB_USER;
    private static String DB_PASS;
    
    public static Connection getConnection() throws SQLException {
        if(connect == null) {
            Driver driver = new Driver();
            
            connect = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
        }
        
        return connect;
    }

    /**
     *
     */
    public mysql() {
        mysql.DB_PASS = "";
        mysql.DB_USER = "root";
        mysql.DB_HOST = "jdbc:mysql://localhost:3306/restoranjava";
    }
}
