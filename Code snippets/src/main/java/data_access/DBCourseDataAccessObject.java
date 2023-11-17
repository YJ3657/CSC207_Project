package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.Course;
import main.java.entity.CourseFactory;
import main.java.entity.User;
import main.java.use_case.courses.AddCourseDataAccessInterface;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


// Need to make updateContents, updateDefiniition, updateStudent, updateContents
public class DBCourseDataAccessObject implements AddCourseDataAccessInterface {
    private Connection conn = null;
    private final Map<String, Course> courses = new HashMap<>();
    private CourseFactory courseFactory;

    public DBCourseDataAccessObject(CourseFactory courseFactory) {
        this.courseFactory = courseFactory;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://csc207:3306",
                    "remoteUser",
                    "thisismysql*");

            ResultSet databases = conn.getMetaData().getCatalogs();

            while (databases.next()) {
                String databaseName = databases.getString(1).toLowerCase();
                if(databaseName.equals("user") || databaseName.equals("group") || databaseName.equals("course")) {
                    continue;
                }
                Course course = this.courseFactory.create(databaseName);
                String sqlOrder = "SELECT chapterno, chaptertitle FROM " + databaseName + ".contents";
                PreparedStatement statement = conn.prepareStatement(sqlOrder);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    int chapterNo = rs.getInt("chapterno");
                    String chapter = rs.getString("chaptertitle");
                    course.getContents().put(chapterNo, chapter);
                }
                courses.put(databaseName, course);
                rs.close();
                statement.close();
            }
            databases.close();
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
    // Saving the new course
    @Override
    public void saveCourse(Course course) {
        courses.put(course.getId(), course);
        save(course);
    }

    public void save(Course course) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://csc207:3306/",
                    "remoteUser",
                    "thisismysql*"
            );

            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + course.getId());
            String sqlOrder = "USE " + course.getId();
            PreparedStatement prestatement = conn.prepareStatement(sqlOrder);
            prestatement.executeQuery();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS question (qustion varchar(50), answer varchar(50), chapterno INT(3))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS definition (word varchar(50), definition varchar(50), chapterno INT(3))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS student (id varchar(50), day INT(3), time_enrolled varchar(50))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS contents (chapterno INT(3), chaptertitle varchar(50))");

            System.out.println("success");
            sqlOrder = "INSERT INTO contents (chapterno, chaptertitle)" +
                    "VALUES (?, ?)";

            int chapterno = 1;
            for(String content : course.getContents().values()) {
                prestatement = conn.prepareStatement(sqlOrder);
                prestatement.setInt(1, chapterno);
                prestatement.setString(2, content);
                chapterno += 1;
                prestatement.executeUpdate();
                prestatement.close();
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
    public boolean existsByID(String courseId) {
        return courses.containsKey(courseId);
    }

    @Override
    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }

    @Override
    public

//    @Override
//    public Map<String, Course> getCourses() {
//        Map<String, Course> studentCourses = new HashMap<>();
//        for (Map.Entry<String, Course> courseEntry: courses.entrySet()) {
//            String courseName = courseEntry.getKey();
//            Course course = courseEntry.getValue();
//            if (course.getStudents().contains(Constants.CURRENT_USER)) {
//                studentCourses.put(courseName, course);
//            }
//            //TODO: maybe in a future improvement, we can add the course object to Student entity's courses attribute (when loading from db and when adding course), and then just return that student's courses
//        }
//        return studentCourses;
//    }


//    public static void main(String[] args) {
//        Connection conn = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://csc207:3306",
//                    "remoteUser",
//                    "thisismysql*");
//
//            ResultSet databases = conn.getMetaData().getCatalogs();
//
//            while (databases.next()) {
//                String databaseName = databases.getString(1).toLowerCase();
//                if(databaseName.equals("user") || databaseName.equals("group") || databaseName.equals("course")) {
//                    continue;
//                }
//                databaseName = databaseName.toUpperCase();
//                Course course = new Course(databaseName);
//                String sqlOrder = "SELECT chapterno, chaptertitle FROM " + databaseName + ".contents";
//                PreparedStatement statement = conn.prepareStatement(sqlOrder);
//                ResultSet rs = statement.executeQuery();
//                while(rs.next()) {
//                    int chapterNo = rs.getInt("chapterno");
//                    String chapter = rs.getString("chaptertitle");
//                    System.out.println(chapterNo);
//                }
//
//
//                System.out.println(databaseName + course.getId());
//                rs.close();
//                statement.close();
//                databases.close();
//            }
//            databases.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class Not Found");
//        } catch (SQLException e) {
//            System.out.println("Check the database");
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                    System.out.println("Connection closed");
//                } catch (SQLException e) {
//                }
//            }
//        }
//    }
    
}


