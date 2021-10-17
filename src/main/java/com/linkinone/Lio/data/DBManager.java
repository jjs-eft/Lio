package com.linkinone.Lio.data;

import java.sql.*;

public class DBManager {

    public static Connection connect() throws SQLException {

            String url = "jdbc:mysql://localhost:3306/liodb?serverTimezone=UTC&characterEncoding=UTF-8";
            return DriverManager.getConnection(url, "root", "lio1232");
    }

    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            if(pstmt != null)
                pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}