package main.java.use_case.notes;

import main.java.entity.Notes;

import java.util.HashMap;
import java.util.List;

public interface NotesDataAccessInterface {

    public void addNotes(Notes notes, String courseId);
    public boolean noteExists(String courseId, String notesTitle);
    public void updateContent(String courseId, String notesTitle, List<String> notesContent);
}
