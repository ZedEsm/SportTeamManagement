package hw6.util;

import java.sql.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/league";
    private static final String USER = "postgres";
    private static final String PASS = "zizoumidou9";

    private Connection connectio;

    public Connection connection() {

        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connectio = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connectio;
    }

}