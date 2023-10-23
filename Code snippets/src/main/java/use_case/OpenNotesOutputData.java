package main.java.use_case;

import java.util.HashMap;

public class OpenNotesOutputData {
    private final HashMap<String, String> notes;

    public OpenNotesOutputData (HashMap<String, String> notes) {
        this.notes = notes;
    }

    public HashMap<String, String> getNotes() {
        return notes;
    }
}
