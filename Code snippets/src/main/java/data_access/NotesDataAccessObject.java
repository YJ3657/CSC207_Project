package main.java.data_access;


import java.util.HashMap;

public class NotesDataAccessObject implements NotesDataAccessInterface {

    private HashMap<String, String> notes = new HashMap<>();
    public NotesDataAccessObject() {
        this.notes = notes;
    }

    @Override
    public HashMap<String, String> getNotes() {
        HashMap<String, String> notes = new HashMap<String, String>();
        notes.put("Title", "Notes taken");
        return notes;
    }


}
