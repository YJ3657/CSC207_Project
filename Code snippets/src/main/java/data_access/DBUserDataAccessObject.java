package main.java.data_access;

import main.java.entity.UserFactory;
import main.java.entity.User;
import main.java.use_case.login.LoginUserDataAccessInterface;
import main.java.use_case.signup.SignupUserDataAccessInterface;
import main.java.use_case.clear_users.ClearUserDataAccessInterface;
import main.java.use_case.update_users.UpdateUserDataAccessInterface;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface,
        UpdateUserDataAccessInterface {

    private Connection conn = null;
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.70.192.192:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );

            String sqlOrder = "SELECT userid, userpassword FROM users";

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

    @Override
    public void saveUser(User user) {
        accounts.put(user.getId(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    public void save() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://100.70.192.192:3306/user",
                    "remoteUser",
                    "thisismysql*"
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

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void clear() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user",
                    "remoteUser",
                    "thisismysql*"
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

    @Override
    public void update(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user",
                    "remoteUser",
                    "thisismysql*"
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
                } catch (SQLException e) { }
            }
        }
    }
}
