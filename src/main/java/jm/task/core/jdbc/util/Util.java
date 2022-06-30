package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {


    //   реализуйте настройку соеденения с БД
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "!220562507ilyan";
    private static final String URL = "jdbc:mysql://localhost:3306/databb";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
        return connection;
    }

    public static Statement getStatement() {

        Statement statement;
        try {
            statement = getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();

        }
        return statement;
    }

    public static SessionFactory getConnection2() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", DRIVER)
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USER_NAME)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("current_session_context_class", "thread")
                        .setProperty("hibernate.connection.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.addAnnotatedClass(User.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;


    }
}
//    public static SessionFactory getConnection2() {
//
//        try {
//            Configuration configuration = new Configuration()
//                    .setProperty("hibernate.connection.driver_class", DRIVER)
//                    .setProperty("hibernate.connection.url", URL)
//                    .setProperty("hibernate.connection.username", USER_NAME)
//                    .setProperty("hibernate.connection.password", PASSWORD)
//                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//
//            sessionFactory = configuration.buildSessionFactory();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//        return sessionFactory;
//    }
