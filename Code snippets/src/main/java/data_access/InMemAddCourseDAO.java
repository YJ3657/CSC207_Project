package main.java.data_access;

import main.java.entity.Course;
import main.java.use_case.courses.AddCourseDataAccessInterface;

import java.util.ArrayList;

public class InMemAddCourseDAO implements AddCourseDataAccessInterface {

    private final ArrayList<String> courses = new ArrayList<>();

    @Override
    public Course getCourse(String courseId) {
        return null; // Test case using this inMem DAO does not use this method currently
    }

    @Override
    public void saveCourse(Course course) {
        this.courses.add(course.getId());
    }

    @Override
    public boolean existsByID(String courseID) {
        return courses.contains(courseID);
    }
}
