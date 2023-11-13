package main.java.entity;

public class CourseFactory {
    public Course create(String courseId) {
        return new Course(courseId);
    }
}
