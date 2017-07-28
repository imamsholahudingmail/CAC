/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author prashant-lappy
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnect
{
    public static Connection conn = null;

    public static void connect()
    {
        try {
        // Load the mysql driver
        String driverName = "com.mysql.jdbc.Driver"; 
        
        Class.forName(driverName);
   
        // Create a connection to the database
        String serverName = "localhost";
        String mydatabase = "db_career_asp";
        String url = "jdbc:mysql://" + serverName +  "/" + mydatabase; // a JDBC url
        String username = "root";
        String password = "";
        conn = DriverManager.getConnection(url, username, password);
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(null,"Cannot connect to database server");
       }
       
    }
}
