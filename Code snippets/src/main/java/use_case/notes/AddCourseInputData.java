package main.java.use_case.notes;

import main.java.entity.Course;

public class AddCourseInputData {
    final private String courseID;

    public AddCourseInputData(String courseID) {
        this.courseID = courseID;
    }

    String getCourseID() {
        return courseID;
    }
}
