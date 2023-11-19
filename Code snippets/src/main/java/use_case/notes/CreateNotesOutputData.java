package main.java.use_case.notes;

import main.java.entity.Notes;

public class CreateNotesOutputData {
    private final Notes notes;

    public CreateNotesOutputData(Notes notes) {
        this.notes = notes;
    }

    public Notes getNotes() {
        return notes;
    }
}
