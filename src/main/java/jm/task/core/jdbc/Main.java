package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();


        userDao.saveUser("Коля", "Боков", (byte) 25);
        userDao.saveUser("Вася", "Котов", (byte) 35);
        userDao.saveUser("Петя", "Петров", (byte) 41);
        userDao.saveUser("Дима", "Дёмин", (byte) 39);

        userDao.removeUserById(2);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
