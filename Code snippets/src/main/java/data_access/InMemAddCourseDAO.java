package main.java.data_access;

import main.java.entity.Course;
import main.java.use_case.courses.AddCourseDataAccessInterface;

import java.util.ArrayList;
import java.util.Map;

public class InMemAddCourseDAO implements AddCourseDataAccessInterface {

    private final ArrayList<Course> courses = new ArrayList<>();

    @Override
    public Course getCourse(String courseId) {
        return null; // Test case using this inMem DAO does not use this method currently
    }



    @Override
    public void saveCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public boolean existsByID(String courseID) {
        ArrayList<String> courseIDs = new ArrayList<>();
        for (Course course: courses) {
            courseIDs.add(course.getId());
        }
        return courseIDs.contains(courseID);
    }

//    @Override
    public Map<String, Course> getCourses() {
        return null;
    }
}
