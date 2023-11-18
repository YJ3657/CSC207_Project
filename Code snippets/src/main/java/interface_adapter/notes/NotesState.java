package main.java.interface_adapter.notes;

import main.java.entity.Notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotesState {
    private String notesTitle;
    private String notesContent;
    private Map<String, List<Notes>> allNotes;

    private ArrayList<String> courses = new ArrayList<>();
    private String selectedcourse;

    private String courseError = null;

    private String notesError = null;

    private String userId;

    public NotesState(NotesState copy) {
        notesTitle = copy.notesTitle;
        notesContent = copy.notesContent;
        courses = copy.courses;
        courseError = copy.courseError;
        notesError = copy.notesError;
        selectedcourse = copy.selectedcourse;
        allNotes = copy.allNotes;
        userId = copy.userId;
    }

    public NotesState() {
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesContent(String notesContent) {
        this.notesContent = notesContent;
    }

    public String getNotesContent() {
        return notesContent;
    }

    public Map<String, List<Notes>> getAllNotes() {
        return allNotes;
    }
    public void setAllNotes(Map<String, List<Notes>> allNotes){this.allNotes = allNotes;}

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void addCourse(String course) {
        this.courses.add(course);
    }

    public void setCourseError(String error) {
        this.courseError = error;
    }

    public void setNotesError(String error) {
        this.notesError = error;
    }

    public String getSelectedCourse(){return selectedcourse;}

    public void setSelectedcourse(String course){this.selectedcourse = course;}

    public String getUserId(){return userId;}

    public void setUserId(String userId){this.userId = userId;}
}
