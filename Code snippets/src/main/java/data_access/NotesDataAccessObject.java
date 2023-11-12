package main.java.data_access;


import main.java.use_case.notes.AddCourseDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class NotesDataAccessObject implements NotesDataAccessInterface, AddCourseDataAccessInterface {


    //TODO: Make LinkedHashmap to preserve order notes were added?
    private HashMap<String, String> notes = new HashMap<>();
    private final ArrayList<String> courses = new ArrayList<>();

    public NotesDataAccessObject() {
    }

    // TODO: Are overrides needed some methods abstract in interfaces?
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


    @Override
    public HashMap<String, String> getNotes() {
        HashMap<String, String> notes = new HashMap<String, String>();
        notes.put("Title", "Notes taken");
        return notes;
    }
}
