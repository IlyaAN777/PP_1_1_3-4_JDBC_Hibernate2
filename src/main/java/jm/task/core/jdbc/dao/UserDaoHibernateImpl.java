package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection2;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getConnection2().openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();


            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users "
                    + "(id BIGINT UNSIGNED NOT NULL auto_increment primary key,"
                    + " username VARCHAR(50) NOT NULL, "
                    + " lastName VARCHAR(50)NOT NULL, "
                    + "age TINYINT(3) NOT NULL );", User.class).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }


    @Override
    public void dropUsersTable() {

        Session session = getConnection2().openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users", User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }


    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = getConnection2().openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getConnection2().openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {

        Session session = getConnection2().openSession();
        List<User> list = null;
        Transaction transaction = null;
        try (session) {

            transaction = session.beginTransaction();
            list = session.createNativeQuery("SELECT * FROM users  ", User.class).getResultList();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getConnection2().openSession();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users;", User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }
}
