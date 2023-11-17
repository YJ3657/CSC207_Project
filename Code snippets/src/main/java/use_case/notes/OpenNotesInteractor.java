package main.java.use_case.notes;

import main.java.entity.Course;
import main.java.use_case.courses.AddCourseDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class OpenNotesInteractor implements OpenNotesInputBoundary {
    final AddCourseDataAccessInterface addCourseDAO;
    final OpenNotesOutputBoundary openNotesPresenter;

    public OpenNotesInteractor(AddCourseDataAccessInterface addCourseDataAccessInterface, OpenNotesOutputBoundary openNotesPresenter) {
        this.addCourseDAO = addCourseDataAccessInterface;
        this.openNotesPresenter = openNotesPresenter;
    }

    @Override
    public void execute() {
        Map<String, Course> courses = addCourseDAO.getCourses();
        OpenNotesOutputData openNotesOutputData = new OpenNotesOutputData(courses);
        openNotesPresenter.presentNotes(openNotesOutputData);
    }
}
