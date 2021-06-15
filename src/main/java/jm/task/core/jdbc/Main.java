package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
//    private static final String INSERT_NEW = "INSERT INTO users VALUES (?,?,?,?)";
//    private static final String INSERT_NEW_AUTO_ID = "INSERT INTO users(name, lastName, age) VALUES('Bruno','Martini',43)";
    private static String tableName;
    private static Long idToDo = 12L;

    private static final String INSERT_NEW = "INSERT INTO " + tableName + "(name, lastName, age) VALUES(?,?,?)";

    private static final String GET_ALL = "SELECT * FROM " + tableName;
    private static final String GET_BY_ID = "SELECT * FROM " + tableName + " WHERE id=9";

    private static final String UPDATE_BY_ID = "UPDATE " + tableName + " SET name='John', lastName='Connor', age=18 WHERE id=" + idToDo;

    private static final String DELETE_ALL = "DELETE from " + tableName;
    private static final String DELETE_BY_ID = "DELETE from " + tableName + " WHERE id=" + idToDo;


//    private static final String DROP = "DROP TABLE ?";
    private static final String DROP = "DROP TABLE $tableName";
//    private static final String DROP = "DROP TABLE " + tableName;

    private static final String CREATE = "CREATE TABLE " + tableName + " (" +
                                        "`id` bigint NOT NULL AUTO_INCREMENT," +
                                        "`name` varchar(45) NOT NULL," +
                                        "`lastName` varchar(45) NOT NULL," +
                                        "`age` tinyint DEFAULT NULL," +
                                        "PRIMARY KEY (`id`)," +
                                        "UNIQUE KEY `id_UNIQUE` (`id`)" +
                                        ") ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3";

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util con1 = new Util();
        con1.setTableName("users33");
        tableName = con1.getTableName();

//        String tableName = con1.getTableName();

        try {
            Statement statement = con1.getConnection().createStatement();

//            statement.execute(DELETE_BY_ID);

//            statement.execute(INSERT_NEW_AUTO_ID);
//            statement.executeUpdate(UPDATE_BY_ID);

            //        statement.execute("DELETE from users");
//        statement.execute("DELETE from users WHERE id=5");

//            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(DELETE_BY_ID);
//            preparedStatement.setLong(1, 3);

//            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(INSERT_NEW);
//            preparedStatement.setLong(1,11);
//            preparedStatement.setString(1, "Bill");
//            preparedStatement.setString(2, "Gates");
//            preparedStatement.setByte(3, (byte) 65);


            String query = DROP.replace("$tableName", con1.getTableName());
//            stmt =conn.prepareStatement(query);

            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(query);
//            preparedStatement.setString(1, con1.getTableName());

//            PreparedStatement preparedStatement = con1.getConnection().prepareStatement("DROP TABLE users22");

//            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(CREATE);

//            PreparedStatement preparedStatement = con1.getConnection().prepareStatement(DELETE_BY_ID);

//            preparedStatement.execute();
            preparedStatement.executeUpdate();

//            ResultSet result1 = preparedStatement.executeQuery(GET_ALL);
//            ResultSet result1 = statement.executeQuery("SELECT * FROM users WHERE id=9");
//            ResultSet result1 = statement.executeQuery("SELECT * FROM users");
            ResultSet result1 = statement.executeQuery(GET_ALL);





            while (result1.next()) {
                User user = new User();
                user.setId(result1.getLong("id"));
                user.setName(result1.getString("name"));
                user.setLastName(result1.getString("lastName"));
                user.setAge(result1.getByte("age"));

                System.out.println(user);

//                long id = result1.getLong("id");
//                System.out.println(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
