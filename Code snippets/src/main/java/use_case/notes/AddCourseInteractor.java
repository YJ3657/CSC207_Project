package main.java.use_case.notes;

import java.time.LocalDateTime;
public class AddCourseInteractor implements AddCourseInputBoundary{

    final AddCourseDataAccessInterface addCourseDAO;

    final AddCourseOutputBoundary addCoursePresenter;

    public AddCourseInteractor(AddCourseDataAccessInterface addCourseDAO, AddCourseOutputBoundary addCoursePresenter) {
        this.addCourseDAO = addCourseDAO;
        this.addCoursePresenter = addCoursePresenter;
    }

    @Override
    public void execute(AddCourseInputData addCourseInputData) {
        String courseID = addCourseInputData.getCourseID();
        if (addCourseDAO.existsByID(courseID)) {
            addCoursePresenter.prepareFailView("Course already exists");
        } else {
            LocalDateTime now = LocalDateTime.now();
            AddCourseOutputData addCourseOutputData = new AddCourseOutputData(courseID, now.toString());
            addCoursePresenter.prepareSuccessView(addCourseOutputData);
            addCourseDAO.saveCourse(courseID);
        }
    }
}
