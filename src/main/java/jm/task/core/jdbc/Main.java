package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Алексей", "Иванов", (byte) 30);
        userService.saveUser("Даша", "Петрова", (byte) 25);
        userService.saveUser("Петр", "Сидоров", (byte) 40);
        userService.saveUser("Милана", "Кузнецова", (byte) 35);
        System.out.println(userService.getAllUsers());

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
        // реализуйте алгоритм здесь
        Util.closeFactory();
    }
}
