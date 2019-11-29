package com.repository;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/track_db";
    private static final String USERNMAME = "postgres";
    private static final String PASSWORD = "apple25";
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";


    public static final BasicDataSource DATA_SOURCE = new BasicDataSource();

    static {
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNMAME);
        DATA_SOURCE.setPassword(PASSWORD);
        DATA_SOURCE.setDriverClassName(DRIVER_CLASS_NAME);
        DATA_SOURCE.setMinIdle(2);
        DATA_SOURCE.setMaxIdle(10);
    }

    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
