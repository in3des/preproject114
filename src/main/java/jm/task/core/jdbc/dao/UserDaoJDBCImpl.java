package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String tableName = "users55";

    private static final String CREATE = "CREATE TABLE $tableName (" +
            "`id` bigint NOT NULL AUTO_INCREMENT," +
            "`name` varchar(45) NOT NULL," +
            "`lastName` varchar(45) NOT NULL," +
            "`age` tinyint DEFAULT NULL," +
            "PRIMARY KEY (`id`)," +
            "UNIQUE KEY `id_UNIQUE` (`id`)" +
            ") ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3";

    private static final String DROP = "DROP TABLE $tableName";

    private static final String INSERT_NEW = "INSERT INTO $tableName (name, lastName, age) VALUES(?,?,?)";

    private static final String GET_ALL = "SELECT * FROM $tableName";

    private static final String DELETE_ALL = "DELETE from $tableName";

    private static final String DELETE_BY_ID = "DELETE from $tableName WHERE id=?";

    Util con1 = new Util();

    List<User> userList1 = new ArrayList<>();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String query = CREATE.replace("$tableName", tableName);
            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            String query = DROP.replace("$tableName", tableName);
            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
            System.err.println("Table was deleted");
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String query = INSERT_NEW.replace("$tableName", tableName);
            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.printf("User with name %s added \n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            String query = DELETE_BY_ID.replace("$tableName", tableName);
            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.printf("User with id %d deleted \n", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            String query = GET_ALL.replace("$tableName", tableName);
            Statement statement = con1.getConnection().createStatement();
//            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(query);
//            preparedStatement.executeUpdate();
            ResultSet result1 = statement.executeQuery(query);

            while (result1.next()) {
                User user = new User();
                user.setId(result1.getLong("id"));
                user.setName(result1.getString("name"));
                user.setLastName(result1.getString("lastName"));
                user.setAge(result1.getByte("age"));
                userList.add(user);
            }

            userList.forEach(System.out::println);
//            System.out.println(userList);
            userList1 = userList;


        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return null;
        return userList1;
    }

    public void cleanUsersTable() {
        try {
            String query = DELETE_ALL.replace("$tableName", tableName);
            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Table data was deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
