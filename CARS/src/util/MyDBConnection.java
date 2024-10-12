package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CAR"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "root"; 

    
    public static Connection getMyDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        }
        return conn;
    }
}
