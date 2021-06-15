package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest1?autoReconnect=true&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private String tableName;

    private Connection connection;


public Util() {
    try {
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        if (!connection.isClosed()) {
            System.out.println("Database is connected!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Cannot load driver class!");
    }
}


    public Connection getConnection() {
        return connection;
    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}



