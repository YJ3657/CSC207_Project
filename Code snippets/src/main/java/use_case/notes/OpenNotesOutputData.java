package main.java.use_case.notes;

import main.java.entity.Course;

import java.util.HashMap;
import java.util.Map;

public class OpenNotesOutputData {
    private final Map<String, Course> notes;

    public OpenNotesOutputData (Map<String, Course> notes) {
        this.notes = notes;
    }

    public Map<String, Course> getNotes() {
        return notes;
    }
}
