package main.java.use_case.notes;

public class CreateNotesInputData {
    final private String title;
    final private String content;
    final private String courseId;
    final private String userId;

    public CreateNotesInputData(String title, String content, String courseId, String userId) {
        this.content = content;
        this.title = title;
        this.courseId = courseId;
        this.userId = userId;
    }

    String getTitle() {
        return title;
    }

    String getContent() {
        return content;
    }

    String getCourseId(){return courseId;}

    String getUserId(){return courseId;}

}
