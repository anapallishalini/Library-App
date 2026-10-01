package org.example;

import java.sql.*;

public class LibraryApp {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "root@2006";

        try (Connection con =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("Connected to database");

            String sql = "SELECT * FROM books";

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                System.out.println("\n--- All Books ---");

                while (rs.next()) {

                    System.out.println(
                            rs.getInt("book_id") + " | " +
                                    rs.getString("title") + " | " +
                                    rs.getString("author") + " | " +
                                    rs.getDouble("price")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}