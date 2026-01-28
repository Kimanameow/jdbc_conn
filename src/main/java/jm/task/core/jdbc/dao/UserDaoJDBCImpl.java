package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();
    String request;

    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() {
        request = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGSERIAL PRIMARY KEY," +
                "name VARCHAR(225) NOT NULL," +
                "lastname VARCHAR(255) UNIQUE NOT NULL," +
                "age SMALLINT NOT NULL" +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        request = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void removeUserById(long id) {
        request = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(User.builder().id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .lastName(rs.getString("lastname"))
                        .age(rs.getByte("age")).build());
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        request = "TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}