package main.java.data_access;


import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class NotesDataAccessObject implements NotesDataAccessInterface {
    

    //TODO: Make LinkedHashmap to preserve order notes were added?
    private HashMap<String, String> notes = new HashMap<>();
    private final ArrayList<String> courses = new ArrayList<>();

    public NotesDataAccessObject() {
    }

    @Override
    public HashMap<String, String> getNotes() {
        HashMap<String, String> notes = new HashMap<String, String>();
        notes.put("Title", "Notes taken");
        return notes;
    }
}
