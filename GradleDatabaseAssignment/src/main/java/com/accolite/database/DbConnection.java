package com.accolite.database;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sarthak_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Acc0@user";

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

