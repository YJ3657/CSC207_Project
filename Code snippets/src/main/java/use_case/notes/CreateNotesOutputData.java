package main.java.use_case.notes;

import main.java.entity.Notes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateNotesOutputData {
    private final Notes notes;
    private final Map<String, List<Notes>> allNotes;

    public CreateNotesOutputData(Notes notes, Map<String, List<Notes>> allNotes) {
        this.notes = notes;
        this.allNotes = allNotes;
    }

    public Notes getNotes() {
        return notes;
    }

    public Map<String, List<Notes>> getAllNotes(){return allNotes;}
}
