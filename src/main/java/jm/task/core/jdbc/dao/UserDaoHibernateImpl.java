package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

public class UserDaoHibernateImpl implements UserDao {

    private static final String tableName = "users55";

//    private static final String DROP = "DROP TABLE $tableName";
    private static final String DROP = "DROP TABLE IF EXISTS $tableName";

    private static final String INSERT_NEW = "INSERT INTO $tableName (name, lastName, age) VALUES(?,?,?)";

    private static final String GET_ALL = "SELECT * FROM $tableName";

    private static final String DELETE_ALL = "DELETE from $tableName";

    private static final String DELETE_BY_ID = "DELETE from $tableName WHERE id=?";

    private static final String CREATE = "CREATE TABLE $tableName (" +
                                        "`id` bigint NOT NULL AUTO_INCREMENT," +
                                        "`name` varchar(45) NOT NULL," +
                                        "`lastName` varchar(45) NOT NULL," +
                                        "`age` tinyint DEFAULT NULL," +
                                        "PRIMARY KEY (`id`)," +
                                        "UNIQUE KEY `id_UNIQUE` (`id`)" +
                                        ") ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3";

    Session session;

    List<User> userList1 = new ArrayList<>();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            String query = CREATE.replace("$tableName", tableName);
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            session.close();
        } catch (HibernateException e ) {
//            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            String query = DROP.replace("$tableName", tableName);
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            session.close();
            System.out.println("Table was deleted");
        } catch (HibernateException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            System.out.printf("User with name %s added \n", name);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from User e where e.id=?1");
            deleteQuery.setParameter(1, id);
//            int deleteResponse = deleteQuery.executeUpdate();
            deleteQuery.executeUpdate();
            System.out.printf("User with id %d deleted \n", id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }



//    @Override
//    public void removeUserById(long id) {
//        try {
//            session = Util.getSessionFactory().openSession();
//            session.beginTransaction();
//            User user = new User();
//            user.setId(id);
//            session.delete(user);
//            System.out.printf("User with id %d deleted \n", id);
//            session.getTransaction().commit();
//            session.close();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//
//    }


    @Override
    public List<User> getAllUsers() {
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
//        Query query  = session.createQuery("select e from User e");
            Query query  = session.createQuery("from User");
            List<User> list = query.list();
            userList1 = list;
            userList1.forEach(System.out::println);
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return userList1;
    }

    @Override
    public void cleanUsersTable() {
        try {
            String query = DELETE_ALL.replace("$tableName", tableName);
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            session.close();
            System.out.println("Table data was deleted");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
