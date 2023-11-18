package main.java.use_case.notes;

public class CreateNotesInputData {
    final private String title;
    final private String content;
    final private String courseId;

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

}
