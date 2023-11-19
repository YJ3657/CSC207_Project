package main.java.use_case.find_user_courses;

import main.java.entity.Notes;

import java.util.List;
import java.util.Map;

public interface FindUserCourseDataAccessInterface {
    public List<String> getUserCourses(String userid);
    Map<String, List<Notes>> getUserNotes(String userId);
}
