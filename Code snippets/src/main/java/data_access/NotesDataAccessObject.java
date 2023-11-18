package main.java.data_access;


import main.java.entity.Course;
import main.java.entity.Notes;
import main.java.entity.NotesFactory;
import main.java.entity.User;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.notes.NotesDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class NotesDataAccessObject implements NotesDataAccessInterface {


    //TODO: Make LinkedHashmap to preserve order notes were added?
    private final HashMap<String, String> notes = new HashMap<>();
    private final ArrayList<String> courses = new ArrayList<>();
    private final NotesFactory notesFactory;
    private final User user;

    public NotesDataAccessObject(NotesFactory notesFactory, User user) {
        this.notesFactory = notesFactory;
        this.user = user;

    }

    // TODO: Are overrides needed some methods abstract in interfaces?
    @Override
    public HashMap<String, String> getNotes() {
        HashMap<String, String> notes = new HashMap<>();
        notes.put("Title", "Notes taken");
        return notes;
    }

    @Override
    public void addNotes(Notes notes, String courseId){

    }

    @Override
    public boolean existsByName(String courseId, String title){
        int result = 0;
        for (Notes i : user.getNotes(courseId)){
            if(i.getTitle().equals(title)){
                result += 1;
            }
        }
        return result == 0;
    }
}
