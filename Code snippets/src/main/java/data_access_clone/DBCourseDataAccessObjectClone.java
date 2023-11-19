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
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
