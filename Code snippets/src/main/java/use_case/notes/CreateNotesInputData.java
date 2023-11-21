package main.java.use_case.notes;

public class CreateNotesInputData {
    final private String title;
    final private String content;
    final private String courseId;
    private boolean overwrite = false;

    public CreateNotesInputData(String title, String content, String courseId, boolean overwrite) {
        this.content = content;
        this.title = title;
        this.courseId = courseId;
        this.overwrite = overwrite;
    }
    public CreateNotesInputData(String title, String content, String courseId) {
        this.content = content;
        this.title = title;
        this.courseId = courseId;
    }

    String getTitle() {
        return title;
    }

    String getContent() {
        return content;
    }

    String getCourseId(){return courseId;}

    Boolean getOverwrite(){return overwrite;}
}
