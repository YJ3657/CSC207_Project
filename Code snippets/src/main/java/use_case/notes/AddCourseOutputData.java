package main.java.use_case.notes;

import main.java.entity.Student;

public class AddCourseOutputData {
    private final String courseID;
    private String creationTime;
    private final Student studentAdded;

    public AddCourseOutputData(String courseID, String creationTime, Student studentAdded) {
        this.courseID = courseID;
        this.studentAdded = studentAdded;
        this.creationTime = creationTime;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public Student getStudentAdded() {return studentAdded;}

//    public void setCreationTime(String creationTime) {
//        this.creationTime = creationTime;
//    }

}
