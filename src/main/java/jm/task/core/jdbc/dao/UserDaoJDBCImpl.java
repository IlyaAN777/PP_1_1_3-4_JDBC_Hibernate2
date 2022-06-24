package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {

            getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS user "
                    + "(id BIGINT UNSIGNED NOT NULL auto_increment primary key,"
                    + " name VARCHAR(50) NOT NULL, "
                    + " lastName VARCHAR(50)NOT NULL, "
                    + "age TINYINT(3) NOT NULL );");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {


        try {
           getStatement().executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM user  ")) {

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                list.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public void cleanUsersTable() {
        try {
            getStatement().executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
