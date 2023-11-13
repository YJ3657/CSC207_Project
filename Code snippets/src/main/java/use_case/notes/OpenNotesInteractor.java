package main.java.use_case.notes;

import main.java.entity.Course;
import main.java.use_case.courses.AddCourseDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class OpenNotesInteractor implements OpenNotesInputBoundary {
    final AddCourseDataAccessInterface addCourseDataAccessInterface;
    final OpenNotesOutputBoundary openNotesPresenter;

    public OpenNotesInteractor(AddCourseDataAccessInterface addCourseDataAccessInterface, OpenNotesOutputBoundary openNotesPresenter) {
        this.addCourseDataAccessInterface = addCourseDataAccessInterface;
        this.openNotesPresenter = openNotesPresenter;
    }

    @Override
    public void execute() {
        Map<String, Course> courses = addCourseDataAccessInterface.getCourses();
        OpenNotesOutputData openNotesOutputData = new OpenNotesOutputData(courses);
        openNotesPresenter.presentNotes(openNotesOutputData);
    }
}
