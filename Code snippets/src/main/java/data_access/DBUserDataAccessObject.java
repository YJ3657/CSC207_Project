package main.java.data_access;

import main.java.entity.UserFactory;
import main.java.entity.User;

import java.io.*;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Below must implement interfaces for SignupUserDataAccessInterface, LoginUserDataAccessInterface
public class DBUserDataAccessObject {

    private Connection conn = null;
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;

    public DBUserDataAccessObject(String driverName, String connectionName, UserFactory userFactory) {
        this.userFactory = userFactory;

        try {
            Class.forName(driverName);

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + connectionName,
                    "java",
                    "mysql"
            );

            String sqlOrder = "SELECT userid, username, userpassword FROM users";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String userId = rs.getString("userid");
                String userPw = rs.getString("userpassword");
                User user = userFactory.create(userId, userPw);
                accounts.put(userId, user);
            }
            rs.close();
            statement.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Check the database");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {}
            }
        }
    }

    public void save(User user) {
        accounts.put(user.getId(), user);
        this.save();
    }

    public void save() {}

}
