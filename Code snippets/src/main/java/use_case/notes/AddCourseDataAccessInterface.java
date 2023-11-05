package main.java.use_case.notes;

import java.util.ArrayList;

public interface AddCourseDataAccessInterface {

    public ArrayList<String> getCourses();
    public void saveCourses(ArrayList<String> courses);

    public void saveCourse(String course);

    public boolean existsByID(String CourseID);
}
