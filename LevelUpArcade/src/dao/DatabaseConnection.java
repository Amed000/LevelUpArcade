package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import config.ConfigLoader;

/**
 * Conexión única a MySQL (Singleton simple)
 */
public class DatabaseConnection {

    private static Connection conn;

    public static Connection getConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(
                		ConfigLoader.getProperty("db.url"),
                		ConfigLoader.getProperty("db.user"),
                		ConfigLoader.getProperty("db.password")
                );
            }
        } catch (Exception e) {
            System.out.println("Error conexión DB: " + e.getMessage());
        }

        return conn;
    }
}