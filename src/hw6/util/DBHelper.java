package hw6.util;

import java.sql.*;

public class DBHelper {
    private static final String url = "jdbc:postgresql://localhost:5432/league";
    private static final String user = "postgres";
    private static final String pass = "zizoumidou9";

    private Connection connectio;

    public Connection connection() {

        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connectio = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connectio;
    }

}