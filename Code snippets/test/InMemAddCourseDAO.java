import main.java.app.Constants;
import main.java.entity.*;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemAddCourseDAO implements AddCourseDataAccessInterface, FindUserCourseDataAccessInterface {

    private final ArrayList<Course> courses = new ArrayList<>();
    public final Map<String, User> accounts = new HashMap<>();

    public InMemAddCourseDAO() {
        accounts.put(Constants.CURRENT_USER, new User(Constants.CURRENT_USER, Constants.TEST_USER_PW));
    }

    @Override
    public Course getCourse(String courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public void save(Course course) {
        courses.add(course);
    }

    @Override
    public boolean existsByID(String courseID) {
        ArrayList<String> courseIDs = new ArrayList<>();
        for (Course course: courses) {
            courseIDs.add(course.getId());
        }
        return courseIDs.contains(courseID);
    }

    public Map<String, Course> getCourses() {
        return null;
    }

    @Override
    public List<String> getUserCourses(String userid) {
        return accounts.get(userid).getCourseId();
    }

    @Override
    public Map<String, List<Notes>> getUserNotes(String userId) {
        return accounts.get(userId).getNotes();
    }

    @Override
    public void addCourse(String courseId) {
        User currentUserObj = accounts.get(Constants.CURRENT_USER);
        currentUserObj.setNotes(courseId);
        currentUserObj.addCourse(courseId);
    }
}
