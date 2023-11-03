package main.java.use_case.notes;

public class AddCourseOutputData {
    private final String courseID;
    private String creationTime;

    public AddCourseOutputData(String courseID, String creationTime) {
        this.courseID = courseID;
        this.creationTime = creationTime;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
