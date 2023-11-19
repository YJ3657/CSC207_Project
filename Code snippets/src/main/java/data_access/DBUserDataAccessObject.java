//package main.java.data_access;
//
//import main.java.app.Constants;
//import main.java.entity.DefaultUserFactory;
//import main.java.entity.Notes;
//import main.java.entity.UserFactory;
//import main.java.entity.User;
//import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
//import main.java.use_case.login.LoginUserDataAccessInterface;
//import main.java.use_case.notes.NotesDataAccessInterface;
//import main.java.use_case.signup.SignupUserDataAccessInterface;
//import main.java.use_case.clear_users.ClearUserDataAccessInterface;
//import main.java.use_case.update_users.UpdateUserDataAccessInterface;
//import java.sql.PreparedStatement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.sql.ResultSet;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface,
//        UpdateUserDataAccessInterface, FindUserCourseDataAccessInterface, NotesDataAccessInterface {
//
//    private Connection conn = null;
//    private final Map<String, User> accounts = new HashMap<>();
//    private UserFactory userFactory;
//
//    public DBUserDataAccessObject(UserFactory userFactory) {
//        this.userFactory = userFactory;
//        accounts.put("sample", userFactory.create("sample", "pass"));
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/user",
//                    "remoteUser",
//                    "thisismysql*"
//            );
//
//            String sqlOrder = "SELECT userid, password, groupid1, groupid2, groupid3, groupid4, groupid5, groupid6," +
//                    " groupid7, groupid8, courseid1, courseid2, courseid3, courseid4, courseid5, courseid6, courseid7, courseid8 FROM users";
//
//            PreparedStatement statement = conn.prepareStatement(sqlOrder);
//
//            ResultSet rs = statement.executeQuery();
//
//            while(rs.next()) {
//                String userId = rs.getString("userid");
//                String userPw = rs.getString("password");
//                User user = this.userFactory.create(userId, userPw);
//                for(int i = 1; i <= 8; i++) {
//                    user.getGroupId().add(rs.getString("groupid" + i));
//                }
//                for(int i = 1; i <= 8; i++) {
//                    user.getCourseId().add(rs.getString("courseid" + i));
//                }
//                accounts.put(userId, user);
//            }
//            rs.close();
//            statement.close();
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class Not Found");
//        } catch (SQLException e) {
//            System.out.println("Check the database");
//        } finally {
//            if(conn != null) {
//                try {
//                    conn.close();
//                    System.out.println("Connection closed");
//                } catch (SQLException e) {}
//            }
//        }
//    }
//
//    @Override
//    public void saveUser(User user) {
//        accounts.put(user.getId(), user);
//        this.save();
//    }
//
//    @Override
//    public User get(String username) {
//        return accounts.get(username);
//    }
//
//    public void save() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/user",
//                    "remoteUser",
//                    "thisismysql*"
//            );
//            for(User user : accounts.values()) {
//                String sqlOrder = "INSERT IGNORE INTO users (userid, password, groupid1, groupid2, groupid3, groupid4," +
//                        " groupid5, groupid6, groupid7, groupid8, courseid1, courseid2, courseid3, courseid4, courseid5, " +
//                        "courseid6, courseid7, courseid8) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//                PreparedStatement statement = conn.prepareStatement(sqlOrder);
//                statement.setString(1, user.getId());
//                statement.setString(2, user.getPassword());
//                for(int i = 3; i <= 10; i++) {
//                    if((i - 2) > user.getGroupId().size()) {
//                        statement.setString(i, "NONE");
//                        continue;
//                    }
//                    statement.setString(i, user.getGroupId().get(i - 3));
//                }
//                for(int i = 11; i <= 18; i++) {
//                    if((i - 10) > user.getCourseId().size()) {
//                        statement.setString(i, "NONE");
//                        continue;
//                    }
//                    statement.setString(i, user.getCourseId().get(i - 11));
//                }
//                statement.executeUpdate();
//            }
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class Not Found");
//        } catch (SQLException e) {
//            System.out.println("Check the database");
//        } finally {
//            if(conn != null) {
//                try {
//                    conn.close();
//                    System.out.println("Connection closed");
//                } catch (SQLException e) {}
//            }
//        }
//    }
//
//    @Override
//    public boolean existsByName(String identifier) {
//        return accounts.containsKey(identifier);
//    }
//
//    @Override
//    public void clear() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/user",
//                    "remoteUser",
//                    "thisismysql*"
//            );
//            String sqlOrder = "DELETE FROM user.users";
//
//            PreparedStatement statement = conn.prepareStatement(sqlOrder);
//            statement.executeUpdate();
//            statement.close();
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class Not Found");
//        } catch (SQLException e) {
//            System.out.println("Check the database");
//        } finally {
//            if(conn != null) {
//                try {
//                    conn.close();
//                    System.out.println("Connection closed");
//                } catch (SQLException e) {}
//            }
//        }
//    }
//
//    @Override
//    public void update(User user) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/user",
//                    "remoteUser",
//                    "thisismysql*"
//            );
//            StringBuilder sqlOrder = new StringBuilder()
//                    .append("UPDATE user.users SET ")
//                    .append("userid=?, ")
//                    .append("password=?, ");
//
//            for(int i = 1; i <= user.getGroupId().size(); i++) {
//                sqlOrder.append("groupid").append(i).append("=?, ");
//            }
//
//            for(int i = 1; i <= user.getCourseId().size(); i++) {
//                if (i == user.getCourseId().size()) {
//                    sqlOrder.append("courseid").append(i).append("=?");
//                }
//                else {
//                    sqlOrder.append("courseid").append(i).append("=?, ");
//                }
//            }
//            PreparedStatement statement = conn.prepareStatement(sqlOrder.toString());
//
//            statement.setString(1, user.getId());
//            statement.setString(2, user.getPassword());
//
//            for(int i = 0; i < user.getGroupId().size(); i++) {
//                statement.setString(i + 3, user.getGroupId().get(i));
//            }
//            for(int i = 0; i < user.getCourseId().size(); i++) {
//                statement.setString(i + 3 + user.getGroupId().size(), user.getCourseId().get(i));
//            }
//            statement.executeUpdate();
//            statement.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class Not Found");
//        } catch (SQLException e) {
//            System.out.println("Check the database");
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                    System.out.println("Connection closed");
//                } catch (SQLException e) { }
//            }
//        }
//    }
//    @Override
//    public List<String> getUserCourses(String userid) {
//        return accounts.get(userid).getCourseId();
//    }
//
//    public Map<String, List<Notes>> getUserNotes(String userId){
//        return accounts.get(userId).getNotes();
//    }
//
//    @Override
//    public void addNotes(Notes notes, String courseId){
//        accounts.get(Constants.CURRENT_USER).setNotes(notes, courseId);
//        this.save();
//    }
//
//}

//TODO:

package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.*;
import main.java.use_case.clear_users.ClearUserDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.login.LoginUserDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.use_case.signup.SignupUserDataAccessInterface;
import main.java.use_case.update_users.UpdateUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface,
        UpdateUserDataAccessInterface, FindUserCourseDataAccessInterface, NotesDataAccessInterface {

    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;
    public DBUserDataAccessObject(UserFactory userFactory){
        this.userFactory = userFactory;
        try{
            File f = new File("user_data.txt");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String row;

            while((row = reader.readLine()) != null){
                String[] info = row.split(",");
                User user = this.userFactory.create(info[0], info[1]);
                accounts.put(info[0], user);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void clear() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("user_data.txt"));
            for (String key: accounts.keySet()){
                bw.write("");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getUserCourses(String userid) {
        return accounts.get(userid).getGroupId();
    }

    @Override
    public Map<String, List<Notes>> getUserNotes(String userId) {
        return null;
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public boolean existsByName(String studentId) {
        return accounts.containsKey(studentId);
    }

    @Override
    public void saveUser(User user) {
        accounts.put(user.getId(), user);
        this.save();
    }

    @Override
    public void update(User user) {
        accounts.put(user.getId(), user);
        this.save();
    }

    public void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("user_data.txt"));
            for (String key: accounts.keySet()){
                bw.write(accounts.get(key).getId() + "," + accounts.get(key).getPassword());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNotes(Notes notes, String courseId) {
        accounts.get(Constants.CURRENT_USER).setNotes(notes, courseId);
        this.save();
    }


}

