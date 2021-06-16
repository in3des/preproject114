package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest1?autoReconnect=true&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private String tableName;

    private Connection connection;

    private static SessionFactory sessionFactory;

//    private static final SessionFactory sessionFactory = buildSessionFactory();
//    private static ServiceRegistry serviceRegistry;

//    public HibernateSessionFactoryUtil() {}


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


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.setProperty(Environment.HBM2DDL_AUTO,"update");
//                properties.setProperty(Environment.HBM2DDL_AUTO,"create-drop");
//                properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.setProperty(Environment.USER, "root");
                properties.setProperty(Environment.PASS, "root");
                properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest1?autoReconnect=true&useSSL=false");
                properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.setProperty(Environment.SHOW_SQL, "true");
                properties.setProperty(Environment.POOL_SIZE, "1");

                Configuration configuration = new Configuration();
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

                if (!sessionFactory.isClosed()) {
                    System.out.println("Hibernate - Database is connected!");
                }


            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Hibernate - Cannot connect to database!");
            }
        }
        return sessionFactory;
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



