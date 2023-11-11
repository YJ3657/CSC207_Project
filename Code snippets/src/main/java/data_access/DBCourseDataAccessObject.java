package main.java.data_access;

import java.io.*;

import main.java.entity.Course;
import main.java.entity.CourseFactory;
import main.java.entity.User;
import main.java.entity.UserFactory;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCourseDataAccessObject implements AddCourseDataAccessInterface {
    private Connection conn = null;
    private final Map<String, Course> courses = new HashMap<>();
    private CourseFactory courseFactory;

    public DBCourseDataAccessObject(CourseFactory courseFactory) {
        this.courseFactory = courseFactory;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://100.70.192.192:3306",
                    "remoteUser",
                    "thisismysql*");

            ResultSet databases = conn.getMetaData().getCatalogs();

            while (databases.next()) {
                String databaseName = databases.getString(1).toLowerCase();
                if(databaseName.equals("user") || databaseName.equals("group") || databaseName.equals("course")) {
                    continue;
                }
                Course course = this.courseFactory.create(databaseName);
                String sqlOrder = "USE " + databaseName;
                PreparedStatement statement = conn.prepareStatement(sqlOrder);
                statement.executeQuery();
                statement.close();
                sqlOrder = "SELECT chapterno, chapter FROM " + databaseName;
                statement = conn.prepareStatement(sqlOrder);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    int chapterNo = resultSet.getInt("chapterno");
                    String chapter = resultSet.getString("chapter");
                    course.getContents().put(chapterNo, chapter);
                }
                courses.put(databaseName, course);
                resultSet.close();
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

    @Override
    public void saveCourse(Course course) {
        courses.put(course.getId(), course);
        this.save(course);
    }

    public void save(Course course) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://100.70.192.192:3306/" + course.getId(),
//                    "remoteUser",
//                    "thisismysql*"
//            );
//            String sqlOrder = "INSERT INTO contents ()" +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            for(User user : accounts.values()) {
//                PreparedStatement statement = conn.prepareStatement(sqlOrder);
//                statement.setString(1, user.getId());
//                statement.setString(2, user.getPassword());
//                for(int i = 3; i <= 10; i++) {
//                    statement.setString(i, user.getGroupId().get(i - 3));
//                }
//                for(int i = 10; i <= 17; i++) {
//                    statement.setString(i, user.getCourseId().get(i - 10));
//                }
//                statement.executeUpdate();
//                statement.close();
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
    }

    @Override
    public boolean existsByID(String courseId) {
        return courses.containsKey(courseId);
    }

    @Override
    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }
}
