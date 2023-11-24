package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.*;
import main.java.use_case.add_Definition.DefinitionDataAccessInterface;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.quiz.QuizDataAccessInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Need to make updateContents, updateDefiniition, updateStudent, updateContents
public class DBCourseDataAccessObject implements AddCourseDataAccessInterface, DefinitionDataAccessInterface {
    private Connection conn = null;
    private final Map<String, Course> courses = new HashMap<>();
    private CourseFactory courseFactory;

    private DefinitionFactory definitionFactory;

    public DBCourseDataAccessObject(CourseFactory courseFactory, DefinitionFactory definitionFactory) {
        this.courseFactory = courseFactory;
        this.definitionFactory = definitionFactory;

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


            for(int chapterNo : course.getContents().keySet()) {
                prestatement = conn.prepareStatement(sqlOrder);
                prestatement.setInt(1, chapterNo);
                prestatement.setString(2, course.getContents().get(chapterNo));
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
    public void save(int chapterNumber, String term, String definition, String userId, String courseId) {
        Definition newDefinition = this.definitionFactory.create(term, definition);
        courses.get(courseId).addDefinition(chapterNumber, newDefinition);
    }

    @Override
    public List<Definition> getDefinitions(int chapterNumber, String courseId) {
        try {
            return courses.get(courseId).getDefinitions(chapterNumber);
        } catch(NullPointerException e){
            return null;
        }
    }


    //

}

// TODO:

//package main.java.data_access;
//
//import main.java.entity.Course;
//import main.java.entity.CourseFactory;
//import main.java.use_case.courses.AddCourseDataAccessInterface;
//
//import java.util.HashMap;
//
//import java.util.Map;
//import java.io.*;
//
//public class DBCourseDataAccessObject implements AddCourseDataAccessInterface {
//
//    private final Map<String, Course> courses = new HashMap<>();
//    private CourseFactory courseFactory;
//
//    public DBCourseDataAccessObject(CourseFactory courseFactory){
//        this.courseFactory = courseFactory;
//        try{
//            File f = new File("course_data.txt");
//            BufferedReader reader = new BufferedReader(new FileReader(f));
//            String row;
//
//            while((row = reader.readLine()) != null){
//                Course course = this.courseFactory.create(row);
//                courses.put(row, course);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Course getCourse(String courseId) {
//        return courses.get(courseId);
//    }
//
//    @Override
//    public void saveCourse(Course course) {
//        courses.put(course.getId(), course);
//        save(course);
//    }
//
//    @Override
//    public boolean existsByID(String courseId) {
//        return courses.containsKey(courseId);
//    }
//
//    public void save(Course course) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter("course_data.txt"));
//            for (String key: courses.keySet()){
//                bw.write(key);
//                bw.newLine();
//            }
//            bw.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


