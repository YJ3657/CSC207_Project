package main.java.use_case.notes;

import main.java.entity.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenNotesOutputData {
    private final List<String> notes;

    public OpenNotesOutputData (List<String> notes) {
        this.notes = notes;
    }

    public List<String> getNotes() {
        return notes;
    }
}
