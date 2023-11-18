package main.java.interface_adapter.notes;

import main.java.entity.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesState {
    private List<String> notes = new ArrayList<>();
    private ArrayList<String> courses = new ArrayList<>();

    private String courseError = null;

    public NotesState(NotesState copy) {
        notes = copy.notes;
        courses = copy.courses;
        courseError = copy.courseError;
    }

    public NotesState() {
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void addCourse(String course) {
        this.courses.add(course);
    }

    public void setCourseError(String error) {
        this.courseError = error;
    }
}
