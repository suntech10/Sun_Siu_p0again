package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private static final String dbURL = "jdbc:postgresql://database-1.ca5kwxumozsn.us-east-2.rds.amazonaws.com/postgres";
    // "jdbc:postgresql://projects.cq72bviehvvn.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=project0";
    private static final String username = "postgres";
    private static final String password = "Sunrev89$";
    private static Connection instance;

    private ConnectionService() {
    }


    public static Connection getInstance() {
        try {
            // register driver
            Class.forName("org.postgresql.Driver");

            instance = DriverManager.getConnection(dbURL, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
