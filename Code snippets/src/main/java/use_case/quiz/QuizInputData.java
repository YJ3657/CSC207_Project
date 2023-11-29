package main.java.use_case.quiz;

public class QuizInputData {
    private final String courseId;
    public QuizInputData(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}
