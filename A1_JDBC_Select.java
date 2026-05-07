/*
 * ============================================================
 *  BEFORE RUNNING — Execute this SQL in MySQL terminal/Workbench:
 * ============================================================
 *
 *  CREATE DATABASE IF NOT EXISTS example_db;
 *  USE example_db;
 *
 *  CREATE TABLE IF NOT EXISTS employees (
 *      id     INT PRIMARY KEY,
 *      name   VARCHAR(100),
 *      salary DOUBLE
 *  );
 *
 *  INSERT INTO employees VALUES (101, 'Alice', 75000)
 *      ON DUPLICATE KEY UPDATE name = name;
 *
 * ============================================================
 *  ECLIPSE SETUP:
 *  1. Create a Standard Java Project
 *  2. Right-click project → Build Path → Add External JARs
 *     → select mysql-connector-j-8.x.x.jar
 *  3. Paste this file → Run As → Java Application
 * ============================================================
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class A1_JDBC_Select {

    // --- Database Configuration (CHANGE PASSWORD IF NEEDED) ---
    private static final String URL  = "jdbc:mysql://localhost:3306/example_db";
    private static final String USER = "root";
    private static final String PASS = "root";  // ← Change to your MySQL password

    public static void main(String[] args) {

        String sql = "SELECT id, name, salary FROM employees WHERE id = ?";

        // try-with-resources: auto-closes Connection, PreparedStatement, ResultSet
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 101);  // Bind parameter: employee ID 101

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("=== Employee Found ===");
                    System.out.println("ID     : " + rs.getInt("id"));
                    System.out.println("Name   : " + rs.getString("name"));
                    System.out.println("Salary : " + rs.getDouble("salary"));
                } else {
                    System.out.println("No employee found with ID 101.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
