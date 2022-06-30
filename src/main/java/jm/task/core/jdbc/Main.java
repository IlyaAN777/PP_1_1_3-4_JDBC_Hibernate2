package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
    //    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
     //  userDaoHibernate.createUsersTable();
       // userDaoHibernate.dropUsersTable();
//        userDaoHibernate.saveUser("asd","fdsf",(byte) 36);
//        userDaoHibernate.saveUser("af","ff",(byte) 37);
//        userDaoHibernate.saveUser("hfd","dhddsf",(byte) 38);
//        userDaoHibernate.saveUser("rty","fdhff",(byte) 39);
        UserDao userDao = new UserDaoHibernateImpl();
      userDao.createUsersTable();
      //  userDao.dropUsersTable();
//

//        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
//
//        userDao.createUsersTable();
//
//
//        userDao.saveUser("Коля", "Боков", (byte) 25);
//        userDao.saveUser("Вася", "Котов", (byte) 35);
//        userDao.saveUser("Петя", "Петров", (byte) 41);
//        userDao.saveUser("Дима", "Дёмин", (byte) 39);
//
//        userDao.removeUserById(2);
//        System.out.println(userDao.getAllUsers());
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();

    }
}
