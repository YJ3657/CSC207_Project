package main.java.use_case.notes;

import main.java.entity.Notes;

import java.util.HashMap;

public interface NotesDataAccessInterface {
    public HashMap<String, String> getNotes();

    public void addNotes(Notes notes, String courseId);

    public boolean existsByName(String courseId, String title);
}
