package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.*;
import main.java.use_case.notes.NotesDataAccessInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DBNotesDataAccessObject implements NotesDataAccessInterface {
    private Connection conn = null;
    private final Map<String, User> accounts = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private UserFactory userFactory;
    private NotesFactory notesFactory;
    private CourseFactory courseFactory;
    private StudentFactory studentFactory;

    public DBNotesDataAccessObject(UserFactory userFactory, NotesFactory notesFactory, CourseFactory courseFactory,
                                   StudentFactory studentFactory) {
        this.userFactory = userFactory;
        this.notesFactory = notesFactory;
        this.courseFactory = courseFactory;
        this.studentFactory = studentFactory;

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
            System.out.println("Error1");
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

            String sqlOrder = "SELECT userid, courseid, contents, chapterno, title FROM notes";

            PreparedStatement statement = conn.prepareStatement(sqlOrder);

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String userId = rs.getString("userid");
                String courseId = rs.getString("courseid");
                String contents = rs.getString("contents"); // TODO: Yeong jae check this over, I'm assuming this is how it would work if change contents to string
                int chapterNo = rs.getInt("chapterno");
                String title = rs.getString("title");
                Notes note = this.notesFactory.create(userId, courseId, contents, chapterNo, title);
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
            System.out.println("Error 2");
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
                    "jdbc:mysql://localhost:3306",
                    "remoteUser",
                    "thisismysql*"
            );

            ResultSet databases = conn.getMetaData().getCatalogs();
            while (databases.next()) {
                String databaseName = databases.getString(1);
                if(!Pattern.matches("[A-Z][A-Z][A-Z]\\d\\d\\d", databaseName)) {
                    continue;
                }
                Course course = this.courseFactory.create(databaseName);

                String sqlOrder = "SELECT chapterno, content FROM " + databaseName + ".contents";
                PreparedStatement statement = conn.prepareStatement(sqlOrder);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    int chapterNo = rs.getInt("chapterno");
                    String content = rs.getString("content");
                    Map<Integer, String> contents = course.getContents();
                    if(contents.containsKey(chapterNo)) {
                        continue;
                    }
                    contents.put(chapterNo, content);
                }
                sqlOrder = "SELECT studentid, time_enrolled FROM " + databaseName + ".students";
                statement = conn.prepareStatement(sqlOrder);
                rs = statement.executeQuery();
                while(rs.next()) {
                    String studentId = rs.getString("studentid");
                    String timeEnrolled = rs.getString("time_enrolled");
                    course.getStudents().add(this.studentFactory.create(studentId, timeEnrolled));
                }

                courses.put(databaseName, course);
                rs.close();
                statement.close();

            }

            databases.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 3");
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

    public void save(User user, Course course) {
        accounts.put(user.getId(), user);
        courses.put(course.getId(), course);
        this.save();
    }

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
                    String newsqlOrder = "INSERT IGNORE INTO notes (userid, courseid, contents, chapterno, title) " +
                            "VALUES (?, ?, ?, ?, ?);";
                    for(Notes notes : user.getNotes().get(courseId)) {
                        PreparedStatement newStatement = conn.prepareStatement(newsqlOrder);
                        newStatement.setString(1, user.getId());
                        newStatement.setString(2, courseId);
                        newStatement.setObject(3, notes.getContents());
                        newStatement.setInt(4, notes.getChapterno());
                        newStatement.setString(5, notes.getTitle());
                        newStatement.executeUpdate();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 4");
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {}
            }
        }

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    "remoteUser",
                    "thisismysql*"
            );

            for(String courseId : this.courses.keySet()) {
                Course course = courses.get(courseId);
                Statement statement = conn.createStatement();
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + course.getId().toUpperCase());
                String sqlOrder = "USE " + course.getId().toUpperCase();
                PreparedStatement prestatement = conn.prepareStatement(sqlOrder);
                prestatement.executeQuery();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS questions (chapterno INT(3), question varchar(50), answer varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS definitions (chapterno INT(3), word varchar(50), definition varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS contents (chapterno INT(3), content varchar(50))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (studentid varchar(50), time_enrolled varchar(50))");

                sqlOrder = "INSERT IGNORE INTO contents (chapterno, content)" +
                        "VALUES (?, ?)";

                for (int chapterno : course.getContents().keySet()) {
                    prestatement = conn.prepareStatement(sqlOrder);
                    prestatement.setInt(1, chapterno);
                    prestatement.setString(2, course.getContents().get(chapterno));
                    prestatement.executeUpdate();
                    prestatement.close();
                }

                sqlOrder = "INSERT IGNORE INTO students (studentid, time_enrolled)" +
                        "VALUES (?, ?)";

                for (Student student : course.getStudents()) {
                    prestatement = conn.prepareStatement(sqlOrder);
                    prestatement.setString(1, student.getStudentid());
                    prestatement.setString(2, student.getTimeEnrolled());
                    prestatement.executeUpdate();
                    prestatement.close();
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 5");
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

    public boolean noteExists(String courseId, String notesTitle){
        if (!(accounts.get(Constants.CURRENT_USER).getNotes().isEmpty())) {
            if (accounts.get(Constants.CURRENT_USER).getNotes().containsKey(courseId)) {
                List<String> titles = new ArrayList<>();
                for (Notes i : accounts.get(Constants.CURRENT_USER).getNotes().get(courseId)) {
                    titles.add(i.getTitle());
                }
                return titles.contains(notesTitle);
            } else {
                return false;
            }
        }
        return false;
    }

    public void updateContent(String courseId, String notesTitle, String notesContent){
        for (Notes note : accounts.get(Constants.CURRENT_USER).getNotes().get(courseId)) {
            if (note.getTitle().equals(notesTitle)){
                note.setContents(notesContent);
            }
        }
        this.save();
    }

    @Override
    public void deleteNotes(Notes tbd, String courseId) {
        if(Constants.CURRENT_USER_OBJ.getNotes().get(courseId).size() == 1){
            Constants.CURRENT_USER_OBJ.getNotes().get(courseId).clear();
        } else {
            Constants.CURRENT_USER_OBJ.getNotes().get(courseId).
                    remove(tbd);
            this.save();
        }
    }

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
            System.out.println("Error 6");
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
                    .append("content=?, ")
                    .append("chapterno=?, ")
                    .append("title=?");

            for(String courseId : user.getNotes().keySet()) {
                for(Notes note: user.getNotes().get(courseId)) {
                    statement = conn.prepareStatement(sqlOrder.toString());
                    statement.setString(1, user.getId());
                    statement.setString(2, courseId);
                    statement.setObject(3, note.getContents());
                    statement.setInt(4, note.getChapterno());
                    statement.setString(5, note.getTitle());
                }
                statement.executeUpdate();
            }
            statement.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error 7");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) { }
            }
        }
    }

    public List<String> getUserCourses(String userid) {
        return accounts.get(userid).getCourseId();
    }

    public Map<String, List<Notes>> getUserNotes(String userId){
        return accounts.get(userId).getNotes();
    }
    // Shouldn't we have userId parameter instead?

    public void addNotes(Notes note, String courseId){
        accounts.get(Constants.CURRENT_USER).setNotes(note, courseId);
        courses.put(courseId, new Course(courseId));
        courses.get(courseId).getContents().put(note.getChapterno(), note.getTitle());
        this.save();
    }
    public void addCourse(String courseId){
        User currentUserObj = accounts.get(Constants.CURRENT_USER);
        currentUserObj.setNotes(courseId); //TODO: Question: User vs Student
        currentUserObj.addCourse(courseId);
        this.save();
    }
}
