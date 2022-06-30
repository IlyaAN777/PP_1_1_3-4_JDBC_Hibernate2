package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.SessionFactory;
import jakarta.persistence.criteria.CriteriaDelete;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection2;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getConnection2().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();


            session.createNativeQuery("CREATE TABLE IF NOT EXISTS user "
                    + "(id BIGINT UNSIGNED NOT NULL auto_increment primary key,"
                    + " name VARCHAR(50) NOT NULL, "
                    + " lastName VARCHAR(50)NOT NULL, "
                    + "age TINYINT(3) NOT NULL );", User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();

        }

    }


    @Override
    public void dropUsersTable() {
        Session session = getConnection2().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user", User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = getConnection2().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
