package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Property;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String PASS = "0101";
    private static final String USER = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static SessionFactory getFactory() {

        Configuration config = new Configuration();

        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", URL);
        properties.put("hibernate.connection.username", USER);
        properties.put("hibernate.connection.password", PASS);
        config.setProperties(properties);
        config.addAnnotatedClass(jm.task.core.jdbc.model.User.class);

        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        return config.buildSessionFactory(sr);
    }
}// реализуйте настройку соеденения с БД