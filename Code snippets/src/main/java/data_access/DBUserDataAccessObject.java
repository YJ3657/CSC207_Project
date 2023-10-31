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
    private String driverName;
    private String connectionName;

    public DBUserDataAccessObject(String driverName, String connectionName, UserFactory userFactory) {
        this.userFactory = userFactory;
        this.driverName = driverName;
        this.connectionName = connectionName;
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
                User user = this.userFactory.create(userId, userPw);
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

    public void save() {
        try {
            Class.forName(this.driverName);
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + this.connectionName,
                    "java",
                    "mysql"
            );
            String sqlOrder = "INSERT INTO users (userid, password, groupid1, groupid2, groupid3, groupid4," +
                    " groupid5, groupid6, groupid7, groupid8, courseid1, courseid2, courseid3, courseid4, courseid5, " +
                    "courseid6, courseid7, courseid8)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            for(User user : accounts.values()) {
                PreparedStatement statement = conn.prepareStatement(sqlOrder);
                statement.setString(1, user.getId());
                statement.setString(2, user.getPassword());
                for(int i = 3; i <= 10; i++) {
                   statement.setString(i, user.getGroupId().get(i - 3));
                }
                for(int i = 10; i <= 17; i++) {
                    statement.setString(i, user.getCourseId().get(i - 10));
                }
                statement.executeUpdate();
                statement.close();
            }
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

    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }


    public void clear() {
        try {
            Class.forName(this.driverName);
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + this.connectionName,
                    "java",
                    "mysql"
            );
            String sqlOrder = "DELETE * FROM users";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);
            statement.executeUpdate();
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

    public void update(User user) {
        try {
            Class.forName(this.driverName);
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + this.connectionName,
                    "java",
                    "mysql"
            );
            StringBuilder sqlOrder = new StringBuilder()
                    .append("UPDATE users SET")
                    .append("userid=? ")
                    .append("password=?, ");

            for(int i = 1; i <= 8; i++) {
                sqlOrder.append("groupid").append(i).append("=?, ");
            }

            for(int i = 1; i <= 8; i++) {
                sqlOrder.append("courseid").append(i).append("=?, ");
            }

            PreparedStatement statement = conn.prepareStatement(sqlOrder.toString());

            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());

            for(int i = 3; i <= 10; i++) {
                statement.setString(i, user.getGroupId().get(i - 3));
            }
            for(int i = 10; i <= 17; i++) {
                statement.setString(i, user.getCourseId().get(i - 10));
            }

            statement.executeUpdate();
            statement.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Check the database");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {
                }
            }
        }
    }


}
