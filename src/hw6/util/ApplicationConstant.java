package hw6.util;

import java.sql.Connection;

public class ApplicationConstant {
    private static Connection connection = new DBHelper().connection();

    public static Connection getConnection() {
        return connection;
    }
}
