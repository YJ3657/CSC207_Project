package main.java.use_case.notes;

import main.java.entity.Notes;

public class CreateNotesOutputData {
    private final Notes notes;

    private boolean useCaseFailed;

    public CreateNotesOutputData(Notes notes, boolean useCaseFailed) {
        this.notes = notes;
        this.useCaseFailed = useCaseFailed;
    }

    public Notes getNotes() {
        return notes;
    }
}
