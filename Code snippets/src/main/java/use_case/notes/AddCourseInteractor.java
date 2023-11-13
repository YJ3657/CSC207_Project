package main.java.use_case.notes;

import main.java.app.Constants;
import main.java.entity.Course;
import main.java.entity.CourseFactory;
import main.java.use_case.courses.AddCourseDataAccessInterface;

//import java.lang.constant.Constable;
import java.time.LocalDateTime;
public class AddCourseInteractor implements AddCourseInputBoundary{

    final AddCourseDataAccessInterface addCourseDAO;

    final AddCourseOutputBoundary addCoursePresenter;
    private final CourseFactory courseFactory;

    public AddCourseInteractor(AddCourseDataAccessInterface addCourseDAO, AddCourseOutputBoundary addCoursePresenter,
                               CourseFactory courseFactory) {
        this.addCourseDAO = addCourseDAO;
        this.addCoursePresenter = addCoursePresenter;
        this.courseFactory = courseFactory;
    }

    @Override
    public void execute(AddCourseInputData addCourseInputData) {
        String courseID = addCourseInputData.getCourseID();
        if (addCourseDAO.existsByID(courseID)) {
            addCoursePresenter.prepareFailView(Constants.ADD_COURSE_ERROR);
        } else {
            LocalDateTime now = LocalDateTime.now();
            AddCourseOutputData addCourseOutputData = new AddCourseOutputData(courseID, now.toString());
            Course course = courseFactory.create(courseID);
            addCourseDAO.saveCourse(course);
            addCoursePresenter.prepareSuccessView(addCourseOutputData);

        }
    }
}
