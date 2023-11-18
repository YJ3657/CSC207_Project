package main.java.use_case.find_user_courses;

import java.util.List;

public interface FindUserCourseDataAccessInterface {
    public List<String> getUserCourses(String userid);
}
