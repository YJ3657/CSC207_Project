package main.java.interface_adapter.notes;

import java.util.ArrayList;
import java.util.HashMap;

public class NotesState {
    private HashMap<String, String> notes = new HashMap<>();
    private ArrayList<String> courses = new ArrayList<>();

    private String courseError = null;

    public NotesState(NotesState copy) {
        notes = copy.notes;
        courses = copy.courses;
        courseError = copy.courseError;
    }

    public NotesState() {
    }

    public void setNotes(HashMap<String, String> notes) {
        this.notes = notes;
    }

    public HashMap<String, String> getNotes() {
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
