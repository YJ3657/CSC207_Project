package main.java.interface_adapter.add_course;

import main.java.app.Constants;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.notes.NotesState;
import main.java.interface_adapter.notes.NotesViewModel;
import main.java.use_case.courses.add_course.AddCourseOutputBoundary;
import main.java.use_case.courses.add_course.AddCourseOutputData;

public class AddCoursePresenter implements AddCourseOutputBoundary {

    private ViewManagerModel viewManagerModel;

    private final NotesViewModel notesViewModel;

    public AddCoursePresenter(ViewManagerModel viewManagerModel, NotesViewModel notesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.notesViewModel = notesViewModel;
    }

    @Override
    public void prepareSuccessView(AddCourseOutputData data) {
        NotesState notesState = notesViewModel.getState();
        notesState.addCourse(data.getCourseID());
        notesViewModel.setState(notesState);
        notesViewModel.firePropertyChanged(Constants.COURSES_PROPNAME);
    }

    @Override
    public void prepareFailView(String error) {
        NotesState notesState = notesViewModel.getState();
        notesState.setCourseError(error);
        notesViewModel.firePropertyChanged(Constants.ADD_COURSE_ERROR);
    }
}
