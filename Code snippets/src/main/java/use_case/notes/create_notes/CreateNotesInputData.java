package main.java.use_case.notes.create_notes;

import java.util.List;

public class CreateNotesInputData {

    private String userId;
    private String courseId;
    private String contents;
    private int chapterNo;
    private String title;

    private boolean overwrite = false;

    public CreateNotesInputData(String userId, String courseId, String contents, String title, boolean overwrite) {
        this.userId = userId;
        this.courseId = courseId;
        this.contents = contents;
        this.title = title;
        this.overwrite = overwrite;
    }
    public CreateNotesInputData(String title, String contents, String courseId, int chapterNo) {
        this.contents = contents;
        this.title = title;
        this.courseId = courseId;
        this.chapterNo = chapterNo;
    }

    public int getChapterNo() {return chapterNo;}
    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return this.contents;
    }

    public String getCourseId(){return courseId;}

    Boolean getOverwrite(){return overwrite;}
}
