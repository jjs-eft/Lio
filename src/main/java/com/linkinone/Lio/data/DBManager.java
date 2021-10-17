package com.linkinone.Lio.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    public static Connection connect() throws SQLException {
        String url ="jdbc:oracle:thin:@localhost:1521:xe";
        return DriverManager.getConnection(url,"c##whh", "whh");
    }
}
