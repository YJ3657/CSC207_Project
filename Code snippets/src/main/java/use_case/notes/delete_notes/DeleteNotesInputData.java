package main.java.use_case.notes.delete_notes;

import main.java.entity.Notes;

public class DeleteNotesInputData {
    private final String courseId;
    private final String title;
    private final Notes tbd;


    public DeleteNotesInputData(String title, String courseId, Notes tbd) {
        this.title = title;
        this.courseId = courseId;
        this.tbd = tbd;
    }
    public String getTitle() {
        return title;
    }

    public String getCourseId(){return courseId;}
    public Notes getToBeDeleted(){return tbd;}
}
