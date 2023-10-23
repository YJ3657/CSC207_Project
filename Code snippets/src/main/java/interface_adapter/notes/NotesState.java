package main.java.interface_adapter.notes;

import java.util.HashMap;

public class NotesState {
    private HashMap<String, String> notes = new HashMap<>();

    public NotesState(NotesState copy) {
        notes = copy.notes;
    }

    public NotesState() {
    }

    public void setNotes(HashMap<String, String> notes) {
        this.notes = notes;
    }

    public HashMap<String, String> getNotes() {
        return notes;
    }
}
