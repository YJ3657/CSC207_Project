package main.java.interface_adapter.notes;

import main.java.app.Constants;
import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.notes.AddCourseOutputBoundary;
import main.java.use_case.notes.AddCourseOutputData;

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
        notesViewModel.firePropertyChanged(Constants.COURSES_PROPNAME); // TODO: Implement the NotesView's PropertyChange method
    }

    @Override
    public void prepareFailView(String error) {
        NotesState notesState = notesViewModel.getState();
        notesState.setCourseError(error);
        notesViewModel.firePropertyChanged(); // TODO: Implement the NotesView's PropertyChange method
    }
}
