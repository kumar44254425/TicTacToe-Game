package com.tictactoe.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to Online MySQL Database
            con = DriverManager.getConnection(
                    "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12819653",
                    "sql12819653",
                    "pQFBXYPvnf");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

}
