package com.fpt.rentahome.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.fpt.rentahome.DB.DBInfo.*;

public class DBConnection {
    private static Connection connection;

    // Connect to database
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }

    // Disconnect from database
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
