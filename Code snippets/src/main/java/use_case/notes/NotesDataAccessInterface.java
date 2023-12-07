package main.java.use_case.notes;

import main.java.entity.Notes;

public interface NotesDataAccessInterface {

    void addNotes(Notes notes, String courseId);
    boolean noteExists(String courseId, String notesTitle);
    void updateContent(String courseId, String notesTitle, String notesContent);
    void deleteNotes(Notes tbd, String courseId);
}
