package main.java.use_case.notes;

import java.util.List;

public class CreateNotesInputData {

    private String courseId;
    private String content;
    private int chapterNo;
    private String title;

    private boolean overwrite = false;

    public CreateNotesInputData(String courseId, String content, int chapterNo, String title, boolean overwrite) {
        this.courseId = courseId;
        this.content = content;
        this.chapterNo = chapterNo;
        this.title = title;
        this.overwrite = overwrite;
    }
    public CreateNotesInputData(String title, String content, String courseId) {
        this.content = content;
        this.title = title;
        this.courseId = courseId;
    }

    public int getChapterNo() {return chapterNo;}

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return this.content;
    }

    public String getCourseId(){return courseId;}

    Boolean getOverwrite(){return overwrite;}
}
