package main.java.data_access;

import main.java.use_case.notes.AddCourseDataAccessInterface;

import java.util.ArrayList;

public class InMemAddCourseDAO implements AddCourseDataAccessInterface {

    private final ArrayList<String> courses = new ArrayList<>();

    @Override
    public ArrayList<String> getCourses() {
        return courses;
    }

    @Override
    public void saveCourses(ArrayList<String> courses) {
        this.courses.addAll(courses);
    }

    @Override
    public void saveCourse(String course) {
        this.courses.add(course);
    }

    @Override
    public boolean existsByID(String courseID) {
        return courses.contains(courseID);
    }
}
