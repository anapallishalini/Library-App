package org.example;

import java.sql.*;

public class InsertBook {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "root@2006";

        try (Connection con =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("Connected to database");

            String sql =
                    "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setString(1, "Wings of Fire");
                ps.setString(2, "A. P. J. Abdul Kalam");
                ps.setDouble(3, 250.0);

                int rows = ps.executeUpdate();

                System.out.println(rows + " row inserted");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}