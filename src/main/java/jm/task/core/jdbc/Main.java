package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService usrService1 = new UserServiceImpl();

        usrService1.createUsersTable();

        usrService1.saveUser("Bob", "Ferguson", (byte) 34);
        usrService1.saveUser("John", "Connor", (byte) 18);
        usrService1.saveUser("John", "Snow", (byte) 25);
        usrService1.saveUser("Phil", "Dunphy", (byte) 52);

        usrService1.getAllUsers();

        usrService1.removeUserById(3);

        usrService1.cleanUsersTable();

        usrService1.dropUsersTable();

    }

}
