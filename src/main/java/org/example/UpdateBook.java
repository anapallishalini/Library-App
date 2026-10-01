package org.example;

import java.sql.*;

public class UpdateBook {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "root@2006";

        try (Connection con =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("Connected to database");

            String sql =
                    "UPDATE books SET price = ? WHERE book_id = ?";

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setDouble(1, 350.0);
                ps.setInt(2, 2);

                int rows = ps.executeUpdate();

                if (rows == 0) {
                    System.out.println("No book found.");
                } else {
                    System.out.println(rows + " row updated");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}