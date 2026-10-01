package org.example;

import java.sql.*;

public class BonusBook {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "root@2006";

        try (Connection con =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("Connected to database");

            String sql =
                    "SELECT * FROM books WHERE price > ?";

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setDouble(1, 250.0);

                try (ResultSet rs = ps.executeQuery()) {

                    System.out.println("\n--- Books Above 250 ---");

                    while (rs.next()) {

                        System.out.println(
                                rs.getInt("book_id") + " | " +
                                        rs.getString("title") + " | " +
                                        rs.getString("author") + " | " +
                                        rs.getDouble("price")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}