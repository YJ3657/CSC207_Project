package main.java.use_case.notes.open_notes;

import main.java.entity.Course;
import main.java.entity.Notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenNotesOutputData {
    private final Map<String, List<Notes>> notes;
    private final ArrayList<String> courses = new ArrayList<>();

    public OpenNotesOutputData (Map<String, List<Notes>> notes) {
        this.notes = notes;
        courses.addAll(notes.keySet());
    }

    public Map<String, List<Notes>> getNotes() {
        return this.notes;
    }

    public ArrayList<String> getCourses() {
        return this.courses;
    }
}
