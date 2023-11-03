package main.java.use_case.notes;

import java.util.ArrayList;

public interface AddCourseDataAccessInterface {

    public ArrayList<String> getCourses();
    public void saveCourses(ArrayList<String> courses);
}
