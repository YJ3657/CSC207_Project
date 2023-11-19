package main.java.use_case.notes;

import main.java.app.Constants;
import main.java.entity.Course;
import main.java.entity.CourseFactory;
import main.java.use_case.courses.AddCourseDataAccessInterface;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;

//import java.lang.constant.Constable;
import java.time.LocalDateTime;
public class AddCourseInteractor implements AddCourseInputBoundary{

    final FindUserCourseDataAccessInterface addUserCourseDAO;
    final AddCourseDataAccessInterface addCourseDAO;
    final AddCourseOutputBoundary addCoursePresenter;
    private final CourseFactory courseFactory;

    public AddCourseInteractor(FindUserCourseDataAccessInterface addUserCourseDAO, AddCourseDataAccessInterface addCourseDAO, AddCourseOutputBoundary addCoursePresenter,
                               CourseFactory courseFactory) {
        this.addUserCourseDAO = addUserCourseDAO;
        this.addCourseDAO = addCourseDAO;
        this.addCoursePresenter = addCoursePresenter;
        this.courseFactory = courseFactory;
    }

    @Override
    public void execute(AddCourseInputData addCourseInputData) {
        String courseID = addCourseInputData.getCourseID();
        if ((addUserCourseDAO.getUserCourses(Constants.CURRENT_USER) == null || !(addUserCourseDAO.getUserCourses(Constants.CURRENT_USER).containsKey(courseID)))) {
            LocalDateTime now = LocalDateTime.now();
            AddCourseOutputData addCourseOutputData = new AddCourseOutputData(courseID, now.toString());
            Course course = courseFactory.create(courseID);
            course.addStudent(Constants.CURRENT_USER);
            addCourseDAO.saveCourse(course);
            addCoursePresenter.prepareSuccessView(addCourseOutputData);

        } else {
            addCoursePresenter.prepareFailView(Constants.ADD_COURSE_ERROR);
        }
    }
}
