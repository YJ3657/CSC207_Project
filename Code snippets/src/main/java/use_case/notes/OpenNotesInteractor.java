package main.java.use_case.notes;

import main.java.app.Constants;
import main.java.entity.Course;
import main.java.use_case.find_user_courses.FindUserCourseDataAccessInterface;
import main.java.use_case.update_users.UpdateUserDataAccessInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenNotesInteractor implements OpenNotesInputBoundary {
    final FindUserCourseDataAccessInterface userCourseDAO;

    final OpenNotesOutputBoundary openNotesPresenter;

    public OpenNotesInteractor(FindUserCourseDataAccessInterface userCourseDAO, OpenNotesOutputBoundary openNotesPresenter) {
        this.userCourseDAO = userCourseDAO;
        this.openNotesPresenter = openNotesPresenter;
    }

    @Override
    public void execute() {
        List<String> courses = userCourseDAO.getUserCourses(Constants.CURRENT_USER);
        OpenNotesOutputData openNotesOutputData = new OpenNotesOutputData(courses);
        openNotesPresenter.presentNotes(openNotesOutputData);
    }
}
