package main.java.use_case.courses;


import main.java.entity.Course;
import main.java.entity.User;

import java.util.Map;

public interface AddCourseDataAccessInterface {
    public Course getCourse(String courseId);
    public void save(Course course);
    public boolean existsByID(String courseId);

}
