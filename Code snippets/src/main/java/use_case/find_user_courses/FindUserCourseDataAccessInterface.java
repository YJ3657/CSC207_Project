package main.java.use_case.find_user_courses;

import main.java.entity.Notes;

import java.util.List;
import java.util.Map;

public interface FindUserCourseDataAccessInterface {
    public Map<String, List<Notes>> getUserCourses(String userid);
}
