package main.java.use_case.notes;

import main.java.entity.Course;
import main.java.entity.Notes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenNotesOutputData {
    private final Map<String, List<Notes>> notes;

    public OpenNotesOutputData (Map<String, List<Notes>> notes) {
        this.notes = notes;
    }

    public Map<String, List<Notes>> getNotes() {
        return notes;
    }
}
