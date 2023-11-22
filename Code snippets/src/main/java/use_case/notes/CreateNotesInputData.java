package main.java.use_case.notes;

import java.util.List;

public class CreateNotesInputData {

    private String userId;
    private String courseId;
    private List<String> contents;
    private int chapterNo;
    private String title;

    private boolean overwrite = false;

    public CreateNotesInputData(String userId, String courseId, List<String> contents, int chapterNo, String title, boolean overwrite) {
        this.userId = userId;
        this.courseId = courseId;
        this.contents = contents;
        this.chapterNo = chapterNo;
        this.title = title;
        this.overwrite = overwrite;
    }
    public CreateNotesInputData(String title, List<String> contents, String courseId) {
        this.contents = contents;
        this.title = title;
        this.courseId = courseId;
    }

    public int getChapterNo() {return chapterNo;}
    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getContents() {
        return this.contents;
    }

    public String getCourseId(){return courseId;}

    Boolean getOverwrite(){return overwrite;}
}
