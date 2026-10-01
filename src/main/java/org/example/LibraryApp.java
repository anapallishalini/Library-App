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


            System.out.println("\n--- All Books ---");

            String selectSql = "SELECT * FROM book";

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(selectSql)) {

                while (rs.next()) {

                    System.out.println(
                            rs.getInt("book_id") + " | " +
                                    rs.getString("title") + " | " +
                                    rs.getString("author") + " | " +
                                    rs.getDouble("price")
                    );
                }
            }

            String insertSql =
                    "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";

            try (PreparedStatement ps =
                         con.prepareStatement(insertSql)) {

                ps.setString(1, "Wings of Fire");
                ps.setString(2, "A. P. J. Abdul Kalam");
                ps.setDouble(3, 250.0);

                int rows = ps.executeUpdate();

                System.out.println("\n" + rows + " row inserted");
            }

            String updateSql =
                    "UPDATE book SET price = ? WHERE book_id = ?";

            try (PreparedStatement ps =
                         con.prepareStatement(updateSql)) {

                ps.setDouble(1, 300.0);
                ps.setInt(2, 2);

                int rows = ps.executeUpdate();

                System.out.println(rows + " row updated");
            }

            String deleteSql =
                    "DELETE FROM book WHERE book_id = ?";

            try (PreparedStatement ps =
                         con.prepareStatement(deleteSql)) {

                ps.setInt(1, 7);

                int rows = ps.executeUpdate();

                if (rows == 0) {
                    System.out.println("No book found.");
                } else {
                    System.out.println("Book deleted.");
                }
            }


            System.out.println("\n--- Final Books ---");

            String finalSql = "SELECT * FROM book";

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(finalSql)) {

                while (rs.next()) {

                    System.out.println(
                            rs.getInt("book_id") + " | " +
                                    rs.getString("title") + " | " +
                                    rs.getString("author") + " | " +
                                    rs.getDouble("price")
                    );
                }
            }


            String bonusSql =
                    "SELECT * FROM book WHERE price > ?";

            try (PreparedStatement ps =
                         con.prepareStatement(bonusSql)) {

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