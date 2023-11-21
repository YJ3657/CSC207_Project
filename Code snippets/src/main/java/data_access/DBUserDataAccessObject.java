package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.DefaultUserFactory;
import main.java.entity.Notes;
import main.java.entity.UserFactory;
import main.java.entity.NotesFactory;
import main.java.entity.User;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.login.LoginUserDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;
import main.java.use_case.signup.SignupUserDataAccessInterface;
import main.java.use_case.clear_users.ClearUserDataAccessInterface;
import main.java.use_case.update_users.UpdateUserDataAccessInterface;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ClearUserDataAccessInterface,
        UpdateUserDataAccessInterface, FindUserCourseDataAccessInterface, NotesDataAccessInterface {

    private Connection conn = null;
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;
    private NotesFactory notesFactory;

    public DBUserDataAccessObject(UserFactory userFactory, NotesFactory notesFactory) {
        this.userFactory = userFactory;
        this.notesFactory = notesFactory;
        accounts.put("sample", userFactory.create("sample", "pass"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );

            String sqlOrder = "SELECT userid, password, groupid1, groupid2, groupid3, groupid4, groupid5, groupid6," +
                    " groupid7, groupid8, courseid1, courseid2, courseid3, courseid4, courseid5, courseid6, courseid7, courseid8 FROM users";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String userId = rs.getString("userid");
                String userPw = rs.getString("password");
                User user = this.userFactory.create(userId, userPw);
                for(int i = 1; i <= 8; i++) {
                    user.getGroupId().add(rs.getString("groupid" + i));
                }
                for(int i = 1; i <= 8; i++) {
                    user.getCourseId().add(rs.getString("courseid" + i));
                }
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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );

            String sqlOrder = "SELECT userid, courseid, notes, chapterno FROM notes";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String userId = rs.getString("userid");
                String courseId = rs.getString("courseid");
                String notes = rs.getString("notes");
                int chapterNo = rs.getInt("chapterno");
                Notes note = notesFactory.create(courseId, notes, chapterNo);
                Map<String, List<Notes>> userNotes = accounts.get(userId).getNotes();
                if (userNotes.containsKey(courseId)) {
                    userNotes.get(courseId).add(note);
                }
                else {
                    List<Notes> newList = new ArrayList<>();
                    newList.add(note);
                    userNotes.put(courseId, newList);
                }
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
    public void save(User user) {
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
                    "jdbc:mysql://localhost:3306/user",
                    "remoteUser",
                    "thisismysql*"
            );
            for(User user : accounts.values()) {
                String sqlOrder = "INSERT IGNORE INTO users (userid, password, groupid1, groupid2, groupid3, groupid4," +
                        " groupid5, groupid6, groupid7, groupid8, courseid1, courseid2, courseid3, courseid4, courseid5, " +
                        "courseid6, courseid7, courseid8) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement statement = conn.prepareStatement(sqlOrder);
                statement.setString(1, user.getId());
                statement.setString(2, user.getPassword());
                for(int i = 3; i <= 10; i++) {
                    if((i - 2) > user.getGroupId().size()) {
                        statement.setString(i, "NONE");
                        continue;
                    }
                    statement.setString(i, user.getGroupId().get(i - 3));
                }
                for(int i = 11; i <= 18; i++) {
                    if((i - 10) > user.getCourseId().size()) {
                        statement.setString(i, "NONE");
                        continue;
                    }
                    statement.setString(i, user.getCourseId().get(i - 11));
                }
                statement.executeUpdate();

                for(String courseId : user.getNotes().keySet()) {
                    String newsqlOrder = "INSERT IGNORE INTO notes (userid, courseid, notes, chapterno) " +
                            "VALUES (?, ?, ?, ?);";
                    for(Notes notes : user.getNotes().get(courseId)) {
                        PreparedStatement newStatement = conn.prepareStatement(newsqlOrder);
                        newStatement.setString(1, user.getId());
                        newStatement.setString(2, courseId);
                        newStatement.setString(3, notes.getContent());
                        newStatement.setInt(4, notes.getChapterno());
                        newStatement.executeUpdate();
                    }
                }
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

    public boolean noteExists(String courseId, String notesTitle){
        if (!(accounts.get(Constants.CURRENT_USER).getNotes().isEmpty()) &&
                !(accounts.get(Constants.CURRENT_USER).getNotes().get(courseId).isEmpty())) {
            List<String> titles = new ArrayList<>();
            for (Notes i : accounts.get(Constants.CURRENT_USER).getNotes().get(courseId)) {
                titles.add(i.getTitle());
            }
            return titles.contains(notesTitle);
        } else {
            return false;
        }
    }

    public void updateContent(String courseId, String notesTitle, String notesContent){
        for (Notes i : accounts.get(Constants.CURRENT_USER).getNotes().get(courseId)) {
            if (i.getTitle().equals(notesTitle)){
                i.setContent(notesContent);
            }
        }
        this.save();
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
            String sqlOrder = "DELETE FROM user.users";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);
            statement.executeUpdate();

            sqlOrder = "DELETE FROM user.notes";
            statement = conn.prepareStatement(sqlOrder);
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
                    .append("UPDATE user.users SET ")
                    .append("userid=?, ")
                    .append("password=?, ");

            for(int i = 1; i <= user.getGroupId().size(); i++) {
                sqlOrder.append("groupid").append(i).append("=?, ");
            }

            for(int i = 1; i <= user.getCourseId().size(); i++) {
                if (i == user.getCourseId().size()) {
                    sqlOrder.append("courseid").append(i).append("=?");
                }
                else {
                    sqlOrder.append("courseid").append(i).append("=?, ");
                }
            }
            PreparedStatement statement = conn.prepareStatement(sqlOrder.toString());

            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());

            for(int i = 0; i < user.getGroupId().size(); i++) {
                statement.setString(i + 3, user.getGroupId().get(i));
            }
            for(int i = 0; i < user.getCourseId().size(); i++) {
                statement.setString(i + 3 + user.getGroupId().size(), user.getCourseId().get(i));
            }
            statement.executeUpdate();

            sqlOrder = new StringBuilder()
                    .append("UPDATE user.notes SET ")
                    .append("userid=?, ")
                    .append("courseid=?, ")
                    .append("notes=?, ")
                    .append("chapterno=?");

            for(String courseId : user.getNotes().keySet()) {
                for(Notes note: user.getNotes().get(courseId)) {
                    statement = conn.prepareStatement(sqlOrder.toString());
                    statement.setString(1, user.getId());
                    statement.setString(2, courseId);
                    statement.setString(3, note.getContent());
                    statement.setInt(4, note.getChapterno());
                }
                statement.executeUpdate();
            }
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
    @Override
    public List<String> getUserCourses(String userid) {
        return accounts.get(userid).getCourseId();
    }

    public Map<String, List<Notes>> getUserNotes(String userId){
        return accounts.get(userId).getNotes();
    }
    // Shouldn't we have userId parameter instead?
    @Override
    public void addNotes(Notes note, String courseId){
        accounts.get(Constants.CURRENT_USER).setNotes(note, courseId);
        this.save();
    }
    public void addCourse(String courseId){
        accounts.get(Constants.CURRENT_USER).setNotes(courseId);
        this.save();
    }

}
