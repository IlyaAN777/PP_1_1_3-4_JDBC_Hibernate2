package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {


    //   реализуйте настройку соеденения с БД
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "!220562507ilyan";
    private static final String URL = "jdbc:mysql://localhost:3306/databb";


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
// не понял насчет возвращать только connection, как быть со statement?
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

}

